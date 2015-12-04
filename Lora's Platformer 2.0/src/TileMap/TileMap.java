package TileMap;

import java.awt.*;
import java.awt.image.*;
import java.io.*;

import Main.GamePanel;

public class TileMap 
{
	// Position
	private double x;
	private double y;
	
	// Bounds
	private int xmin;
	private int ymin;
	private int xmax;
	private int ymax;
	
	private double tween;
	
	// Map
	private int[][] map;
	private int tileSize;
	private int numRows;
	private int numCols;
	private int width;
	private int height;

	// Tileset
	private BufferedImage tileset;
	private int numTilesAcross;
	private Tile[][] tiles;
	
	// Drawing
	private int rowOffset;
	private int colOffset;
	private int numRowsToDraw;
	private int numColsToDraw;
	
	public TileMap(int tileSize)
	{
		this.tileSize = tileSize;
		numRowsToDraw = GamePanel.HEIGHT / tileSize + 2;
		numColsToDraw = GamePanel.WIDTH / tileSize + 2;
		tween = 1;
	}
	
	public void loadTiles(BufferedImage bufferedImage)
	{
		final int waterBlockStart = 41;
		final int waterBlockEnd = 42;
		try
		{
			tileset = bufferedImage;
					
			numTilesAcross = tileset.getWidth() / tileSize;
			tiles = new Tile[2][numTilesAcross];
			
			BufferedImage subimage;
			for(int col = 0; col < numTilesAcross; col++)
			{
				subimage = tileset.getSubimage(
						col * tileSize,
						0,
						tileSize,
						tileSize
						);
				
//				tiles[0][col] = new Tile(subimage, Tile.NORMAL);
				
				
				if(col == 41 || col == 42)
				{
//					System.out.println("water: col: " + col);
					tiles[0][col] = new Tile(subimage, Tile.NORMAL, true);
				}
				else
				{
//					System.out.println("normal: col: " + col);
					tiles[0][col] = new Tile(subimage, Tile.NORMAL, false);
				}
				
				subimage = tileset.getSubimage(
						col * tileSize,
						tileSize,
						tileSize,
						tileSize
						);
				

				
				tiles[1][col] = new Tile(subimage, Tile.BLOCKED, true);
			}
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void loadMap(String string)
	{
//		JOptionPane.showMessageDialog(null, string);

		try
		{
			InputStream inputStream = getClass().getResourceAsStream(string);
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(inputStream)
					);
//			JOptionPane.showMessageDialog(null, bufferedReader.readLine());
			numCols = Integer.parseInt(bufferedReader.readLine());
			numRows = Integer.parseInt(bufferedReader.readLine());
			map = new int[numRows] [numCols];
			width = numCols * tileSize;
			height = numRows * tileSize;
			
			xmin = GamePanel.WIDTH - width;
			xmax = 0;
			ymin = GamePanel.HEIGHT - height;
			ymax = 0;
			
			String delimiters = "\\s+";
			for(int row = 0; row < numRows; row++)
			{
				String line = bufferedReader.readLine();
				String[] tokens = line.split(delimiters);
				for(int col = 0; col < numCols; col++)
				{
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public int getTileSize()
	{
		return tileSize;
	}
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public void setTween(double newTween)
	{
		tween = newTween;
	}
	
	public int getType(int row, int col)
	{
		if(row == -1 || col == -1 || row >= map.length || col >= map[0].length || row < 0 || col < 0)
		{
			return -5000;
		}
		
		try
		{
			int rc = map[row][col];
			int r = rc / numTilesAcross;
			int c = rc % numTilesAcross;
			return tiles[r][c].getType();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	
	public Tile getTile(int row, int col) throws Exception
	{
		
		if(row == -1 || col == -1 || row >= map.length || col >= map[0].length || row < 0 || col < 0)
		{
			throw new Exception();
		}
		
		int rc = map[row][col];
		int r = rc / numTilesAcross;
		int c = rc % numTilesAcross;
		return tiles[r][c];
	}
	
	public int[][] getMap()
	{
		return map;
	}
	
	public void setMapSingleBlock(int row, int col, int type)
	{
		map[row][col] = type;
	}
	
	public void setPosition(double x, double y)
	{
		this.x += (x - this.x) * tween;
		this.y += (y - this.y) * tween;
		
		fixBounds();
		
		colOffset = (int)-this.x / tileSize;
		rowOffset = (int)-this.y / tileSize;
	}
	
	private void fixBounds()
	{
		if(x < xmin) x = xmin;
		if(y < ymin) y = ymin;
		if(x > xmax) x = xmax;
		if(y > ymax) y = ymax;
	}
	
	public void draw(Graphics2D graphics)
	{
		for(
				int row = rowOffset; 
				row < rowOffset + numRowsToDraw; 
				row++)
		{
			if(row >= numRows) break;
			
			for(
					int col = colOffset; 
					col < colOffset + numColsToDraw; 
					col++)
			{
				if(col >= numCols) break;
				
				// Ignore first tile in the tile set.
				if(map[row][col] == 0) continue;
				if(row == 0 && (col == 43 || col == 44)) continue;
				
				int rc = map[row][col];
				int r = rc / numTilesAcross;
				int c = rc % numTilesAcross;
				
				graphics.drawImage(
						tiles[r][c].getImage(),
						(int)x + col * tileSize,
						(int)y + row * tileSize,
						null
						);
			}
		}
	}
	
}
