import java.util.Scanner;

public class worstFit {
    public static void main(String[] args) {

        System.out.print("\n========== WORSTFIT MEMORY ALLOCATION ALGORITHM==========");

        Scanner sc = new Scanner(System.in);

        // Take the number of blocks as input
        System.out.print("\n\nEnter the number of Blocks: ");
        int numBlocks = sc.nextInt();

        // Take the size of each block
        int[] blockSizes = new int[numBlocks];
        int[] originalBlockSizes = new int[numBlocks]; // To store original block sizes for later display
        System.out.println("\n======== Enter the sizes of Blocks ========");
        for (int i = 0; i < numBlocks; i++) {
            System.out.print("Block Number " + (i + 1) + ": ");
            blockSizes[i] = sc.nextInt();
            originalBlockSizes[i] = blockSizes[i]; // Store original block sizes
        }

        // Take the number of processes
        System.out.print("\nEnter the number of Jobs(Processes): ");
        int numProcesses = sc.nextInt();

        // Take the size of each process
        int[] processSizes = new int[numProcesses];
        System.out.println("\n======== Enter the sizes of Jobs(Processes) ========");
        for (int i = 0; i < numProcesses; i++) {
            System.out.print("Process Number " + (i + 1) + ": ");
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
        System.out.println("\n\n ======== Process Allocation Results ========");
        System.out.println("Process Number\t\tProcess Size\t\tBlock Number");
        for (int i = 0; i < numProcesses; i++) {
            System.out.print((i + 1) + "\t\t\t" + processSizes[i] + "\t\t\t");
            if (allocation[i] != -1) {
                System.out.println(allocation[i] + 1); // Block index (1-based)
            } else {
                System.out.println("Not Allocated for a Block");
            }
        }

        // Display remaining block space after all allocations
        System.out.println("\n======== Remaining Block Sizes ========");
        for (int i = 0; i < numBlocks; i++) {
            System.out.println("Block " + (i + 1) + " \tOriginal Block Size: " + originalBlockSizes[i] +
                    ", \tRemaining Block Space: " + blockSizes[i]);
        }

        sc.close();
    }
}
