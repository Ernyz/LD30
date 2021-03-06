package com.ld30.game.utils;

//A* for libgdx that is simple but optimized.

import com.badlogic.gdx.utils.BinaryHeap;
import com.badlogic.gdx.utils.BinaryHeap.Node;
import com.badlogic.gdx.utils.IntArray;

/** @author Nathan Sweet */
public class AStar {
	
	public static interface Validator {
		public boolean isValid (int x, int y);
	}
	
	private int width, height;
	private BinaryHeap<PathNode> open;
	private PathNode[] nodes;
	int runID;
	private final IntArray path = new IntArray();
	private int targetX, targetY;

	public void setSize (final int width, final int height) {
		this.width = width;
		this.height = height;
		open = new BinaryHeap<PathNode>(width * 4, false);
		nodes = new PathNode[width * height];
	}

	/** Returns x,y pairs that are the path from the target to the start. */
	public IntArray getPath (int startX, int startY, int targetX, int targetY, Validator validator) {
		this.targetX = targetX;
		this.targetY = targetY;

		path.clear();
		open.clear();

		runID++;
		if (runID < 0) runID = 1;

		int index = startY * width + startX;
		PathNode root = nodes[index];
		if (root == null) {
			root = new PathNode(0);
			root.x = startX;
			root.y = startY;
			nodes[index] = root;
		}
		root.parent = null;
		root.pathCost = 0;
		open.add(root, 0);

		int lastColumn = width - 1, lastRow = height - 1;
		while (open.size > 0) {
			PathNode node = open.pop();
			if (node.x == targetX && node.y == targetY) {
				while (node != root) {
					path.add(node.x);
					path.add(node.y);
					node = node.parent;
				}
				break;
			}
			node.closedID = runID;
			int x = node.x;
			int y = node.y;
			if (x < lastColumn) {
				addNode(node, x + 1, y, 10, validator);
				if (y < lastRow) addNode(node, x + 1, y + 1, 14, validator); // Diagonals cost more, roughly equivalent to sqrt(2).
				if (y > 0) addNode(node, x + 1, y - 1, 14, validator);
			}
			if (x > 0) {
				addNode(node, x - 1, y, 10, validator);
				if (y < lastRow) addNode(node, x - 1, y + 1, 14, validator);
				if (y > 0) addNode(node, x - 1, y - 1, 14, validator);
			}
			if (y < lastRow) addNode(node, x, y + 1, 10, validator);
			if (y > 0) addNode(node, x, y - 1, 10, validator);
		}
		
		int diffX = Math.abs(targetX - startX);
		int diffY = Math.abs(targetY - startY);
		
		if (path.size == 0 && diffX > 1 && diffY > 1) {
			return path;
		}
		
		//path.insert(0, targetX);
		//path.insert(0, targetY);
		
		return path;
	}

	private void addNode (PathNode parent, int x, int y, int cost, Validator validator) {
		if (!validator.isValid(x, y)) return;

		int pathCost = parent.pathCost + cost;
		float score = pathCost + Math.abs(x - targetX) + Math.abs(y - targetY);

		int index = y * width + x;
		PathNode node = nodes[index];
		if (node != null && node.runID == runID) { // Node already encountered for this run.
			if (node.closedID != runID && pathCost < node.pathCost) { // Node isn't closed and new cost is lower.
				// Update the existing node.
				open.setValue(node, score);
				node.parent = parent;
				node.pathCost = pathCost;
			}
		} else {
			// Use node from the cache or create a new one.
			if (node == null) {
				node = new PathNode(0);
				node.x = x;
				node.y = y;
				nodes[index] = node;
			}
			open.add(node, score);
			node.runID = runID;
			node.parent = parent;
			node.pathCost = pathCost;
		}
	}

	public int getWidth () {
		return width;
	}

	public int getHeight () {
		return height;
	}

	static private class PathNode extends Node {
		int runID, closedID, x, y, pathCost;
		PathNode parent;

		public PathNode (float value) {
			super(value);
		}
	}
}
