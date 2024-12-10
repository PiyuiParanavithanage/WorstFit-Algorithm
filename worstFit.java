import java.util.Scanner;

public class worstFit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
 
        // Input number of memory blocks
        System.out.print("Enter the number of memory blocks: ");
        int numBlocks = sc.nextInt();
        int[] blockSizes = new int[numBlocks];

        System.out.println("Enter the sizes of memory blocks: ");
        for (int i = 0; i < numBlocks; i++) {
            blockSizes[i] = sc.nextInt();
        }

        // Input number of processes
        System.out.print("Enter the number of processes: ");
        int numProcesses = sc.nextInt();
        int[] processSizes = new int[numProcesses];

        System.out.println("Enter the sizes of processes: ");
        for (int i = 0; i < numProcesses; i++) {
            processSizes[i] = sc.nextInt();
        }

        // Array to store allocation details (-1 indicates not allocated)
        int[] allocation = new int[numProcesses];
        for (int i = 0; i < numProcesses; i++) {
            allocation[i] = -1;
        }

        // Allocate memory to processes using Worst Fit
        for (int i = 0; i < numProcesses; i++) {
            int worstIndex = -1;

            for (int j = 0; j < numBlocks; j++) {
                if (blockSizes[j] >= processSizes[i]) {
                    if (worstIndex == -1 || blockSizes[j] > blockSizes[worstIndex]) {
                        worstIndex = j;
                    }
                }
            }

            // If a suitable block is found
            if (worstIndex != -1) {
                allocation[i] = worstIndex; // Allocate the block to the process
                blockSizes[worstIndex] -= processSizes[i]; // Reduce available block size
            }
        }

        // Display allocation results
        System.out.println("\nProcess Allocation Results:");
        System.out.println("Process No.\tProcess Size\tBlock No.");
        for (int i = 0; i < numProcesses; i++) {
            System.out.print((i + 1) + "\t\t" + processSizes[i] + "\t\t");
            if (allocation[i] != -1) {
                System.out.println(allocation[i] + 1); // Block index (1-based)
            } else {
                System.out.println("Not Allocated");
            }
        }

        sc.close();
    }
}
