import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

class Array extends JPanel {

	public static final int WIN_HEIGHT = SortVisualizer.WIN_HEIGHT;
	public static final int WIN_WIDTH = SortVisualizer.WIN_WIDTH;
	public static final int BAR_WIDTH = SortVisualizer.BAR_WIDTH;
	public static final int BAR_HEIGHT_MULTI = SortVisualizer.BAR_HEIGHT_MULTI;
	public static final int NUM_BARS = SortVisualizer.NUM_BARS;
	private static final int miliSecDelay = 10;

	public static int[] LIST = new int[NUM_BARS];
	public static int[] barColors = new int[NUM_BARS];

	public Array() {
		setBackground(Color.darkGray);
		for (int i = 0; i < LIST.length; i++) {
			LIST[i] = new Random().nextInt(WIN_HEIGHT);
			LIST[i] = i * WIN_HEIGHT / NUM_BARS;
			barColors[i] = 0;
		}
	}

	public void highlightArray() throws InterruptedException {
		for (int i = 0; i < LIST.length; i++) {
			barColors[i] = 100;
			repaint();
			Thread.sleep(miliSecDelay / 3);
		}
	}

	public void reset() throws InterruptedException {
		for (int i = 0; i < barColors.length; i++) {
			if (barColors[i] > 0) {
				barColors[i] = 0;
				repaint();
				Thread.sleep(miliSecDelay);
			}
		}
	}

	public void swap(int firstIndex, int secondIndex) {
		int temp = LIST[firstIndex];
		LIST[firstIndex] = LIST[secondIndex];
		LIST[secondIndex] = temp;

		barColors[firstIndex] = 100;
		barColors[secondIndex] = 100;
		repaint();
	}

	public void bubbleSort() throws InterruptedException {
		for (int i = 0; i < LIST.length - 1; i++) {
			for (int j = 0; j < LIST.length - 1 - i; j++) {
				if (LIST[j] > LIST[j + 1]) {
					swap(j, j + 1);
					Thread.sleep(miliSecDelay / 2);
				}
			}
		}
	}

	public void selectionSort() throws InterruptedException {
		int len = LIST.length;
		for (int i = 0; i < len - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < len; j++) {
				if (LIST[j] < LIST[minIndex]) {
					minIndex = j;
				}
			}
			swap(i, minIndex);
			Thread.sleep(miliSecDelay);
		}
	}

	public void quickSort(int[] arr, int lowIndex, int highIndex) throws InterruptedException {
		if (lowIndex < highIndex) {
			int pivotPoint = findPivotPoint(arr, lowIndex, highIndex);
			quickSort(arr, lowIndex, pivotPoint - 1);
			quickSort(arr, pivotPoint + 1, highIndex);
		}
	}

	public int findPivotPoint(int[] arr, int lowIndex, int highIndex) throws InterruptedException {
		int pivotValue = arr[highIndex];
		int i = lowIndex - 1;
		for (int j = lowIndex; j <= highIndex - 1; j++) {
			if (arr[j] <= pivotValue) {
				i++;
				swap(i, j);
				Thread.sleep(miliSecDelay);
			}
		}
		swap(i + 1, highIndex);
		Thread.sleep(miliSecDelay);
		return i + 1;
	}

	public void shuffle() throws InterruptedException {
		Random rng = new Random();
		for (int i = 0; i < LIST.length; i++) {
			int randIndex = rng.nextInt(NUM_BARS - 1);
			swap(i, randIndex);
			Thread.sleep(miliSecDelay);
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int x = 0; x < LIST.length; x++) {
			int height = LIST[x] * BAR_HEIGHT_MULTI;
			int xBegin = x + (BAR_WIDTH - 1) * x;
			int yBegin = WIN_HEIGHT - height;

			int val = barColors[x] * 2;
			System.out.println(val);
			g.setColor(new Color(255, 255 - val, 255 - val));
			g.fillRect(xBegin, yBegin, BAR_WIDTH - 1, height);
			if (barColors[x] > 0)
				barColors[x] -= 20;
		}
	}
}
