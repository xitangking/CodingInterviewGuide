package SchoolRecruit.jingritoutiao2018.answer;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

class Problem_03_SDEandPM {

	public static class Program {
		public int index;
		public int pm;
		public int start;
		public int rank;
		public int cost;

		public Program(int index, int pmNum, int begin, int rank, int cost) {
			this.index = index;
			this.pm = pmNum;
			this.start = begin;
			this.rank = rank;
			this.cost = cost;
		}
	}

	public static class PmLoveRule implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			if (o1.rank != o2.rank) {
				return o1.rank - o2.rank;
			} else if (o1.cost != o2.cost) {
				return o1.cost - o2.cost;
			} else {
				return o1.start - o2.start;
			}
		}

	}

	public static class BigQueues {
		private List<PriorityQueue<Program>> pmQueues;
		private Program[] heap;
		private int[] indexes;
		private int heapsize;

		public BigQueues(int size) {
			this.heapsize = 0;
			heap = new Program[size];
			indexes = new int[size + 1];
			for (int i = 0; i <= size; i++) {
				indexes[i] = -1;
			}
			pmQueues = new ArrayList<>();
			for (int i = 0; i <= size; i++) {
				pmQueues.add(new PriorityQueue<Program>(new PmLoveRule()));
			}
		}

		public boolean isEmpty() {
			return heapsize == 0;
		}

		public void add(Program program) {
			PriorityQueue<Program> queue = pmQueues.get(program.pm);
			queue.add(program);
			Program head = queue.peek();
			int heapindex = indexes[head.pm];
			if (heapindex == -1) {
				heap[heapsize] = head;
				indexes[head.pm] = heapsize;
				heapInsert(heapsize++);
			} else {
				heap[heapindex] = head;
				heapInsert(heapindex);
			}
		}

		public Program pop() {
			Program head = heap[0];
			PriorityQueue<Program> queue = pmQueues.get(head.pm);
			queue.poll();
			if (queue.isEmpty()) {
				swap(0, heapsize - 1);
				heap[--heapsize] = null;
				indexes[head.pm] = -1;
			} else {
				heap[0] = queue.peek();
			}
			heapify(0);
			return head;
		}

		private void heapInsert(int index) {
			while (index != 0) {
				int parent = (index - 1) / 2;
				if (sdeLoveRule(heap[parent], heap[index]) > 0) {
					swap(parent, index);
					index = parent;
				} else {
					break;
				}
			}
		}

		private void heapify(int index) {
			int left = index * 2 + 1;
			int right = index * 2 + 2;
			int best = index;
			while (left < heapsize) {
				if (sdeLoveRule(heap[left], heap[index]) < 0) {
					best = left;
				}
				if (right < heapsize && sdeLoveRule(heap[right], heap[best]) < 0) {
					best = right;
				}
				if (best == index) {
					break;
				}
				swap(best, index);
				index = best;
				left = index * 2 + 1;
				right = index * 2 + 2;
			}
		}

		private void swap(int index1, int index2) {
			Program p1 = heap[index1];
			Program p2 = heap[index2];
			heap[index1] = p2;
			heap[index2] = p1;
			indexes[p1.pm] = index2;
			indexes[p2.pm] = index1;
		}

		private int sdeLoveRule(Program p1, Program p2) {
			if (p1.cost != p2.cost) {
				return p1.cost - p2.cost;
			} else {
				return p1.pm - p2.pm;
			}
		}

	}

	public static class StartRule implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			return o1.start - o2.start;
		}

	}

	public static int[] workFinish(int pms, int sdes, int[][] programs) {
		PriorityQueue<Program> programsQueue = new PriorityQueue<Program>(new StartRule());
		for (int i = 0; i < programs.length; i++) {
			Program program = new Program(i, programs[i][0], programs[i][1], programs[i][2], programs[i][3]);
			programsQueue.add(program);
		}
		PriorityQueue<Integer> sdeWakeQueue = new PriorityQueue<Integer>();
		for (int i = 0; i < sdes; i++) {
			sdeWakeQueue.add(1);
		}
		BigQueues bigQueues = new BigQueues(pms);
		int finish = 0;
		int[] ans = new int[programs.length];
		while (finish != ans.length) {
			int sdeWakeTime = sdeWakeQueue.poll();
			while (!programsQueue.isEmpty()) {
				if (programsQueue.peek().start > sdeWakeTime) {
					break;
				}
				bigQueues.add(programsQueue.poll());
			}
			if (bigQueues.isEmpty()) {
				sdeWakeQueue.add(programsQueue.peek().start);
			} else {
				Program program = bigQueues.pop();
				ans[program.index] = sdeWakeTime + program.cost;
				sdeWakeQueue.add(ans[program.index]);
				finish++;
			}
		}
		return ans;
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}

	public static void main(String[] args) {
		int pms = 2;
		int sde = 2;
		int[][] programs = { { 1, 1, 1, 2 }, { 1, 2, 1, 1 }, { 1, 3, 2, 2 }, { 2, 1, 1, 2 }, { 2, 3, 5, 5 } };
		int[] ans = workFinish(pms, sde, programs);
		printArray(ans);
	}

}
