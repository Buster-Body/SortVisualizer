import javax.swing.JFrame;

public class SortVisualizer {
	public static final int WIN_WIDTH = 1920;
	public static final int WIN_HEIGHT = 1000;
	public static final int BAR_WIDTH = 10;
	public static final int BAR_HEIGHT_MULTI = 1;
	public static final int NUM_BARS = WIN_WIDTH / BAR_WIDTH;
	private static JFrame window;
	private static Array array;

	public static void main(String... args) throws InterruptedException {
		window = new JFrame("Sorting Visualizer");
		array = new Array();
		window.setVisible(true);
		window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
		window.setSize(WIN_WIDTH + 15, WIN_HEIGHT + 40);
		window.add(array);
		while (true) {
			Thread.sleep(3000);
			array.shuffle();
			array.quickSort(Array.LIST, 0, Array.LIST.length - 1);
			array.highlightArray();
			array.reset();
			Thread.sleep(3000);
			array.shuffle();
			array.selectionSort();
			array.highlightArray();
			array.reset();
			Thread.sleep(3000);
			array.shuffle();
			array.bubbleSort();
			array.highlightArray();
			array.reset();
		}

	}
}