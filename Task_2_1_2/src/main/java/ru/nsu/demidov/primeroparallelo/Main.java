package ru.nsu.demidov.primeroparallelo;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: <mode> [options]");
            System.out.println("Modes:");
            System.out.println("  sequential <numbers...> - sequential check");
            System.out.println("  parallel <threads> <numbers...> - parallel check");
            System.out.println("  stream <numbers...> - parallel stream check");
            System.out.println("  manager - run as manager node");
            System.out.println("  worker - run as worker node");
            System.out.println("  distributed <managerHost> <numbers...> - distributed check");
            return;
        }

        String mode = args[0];
        try {
            switch (mode) {
                case "sequential":
                    int[] seqNumbers = parseNumbers(args, 1);
                    System.out.println(new Subsequent().containsNotPrime(seqNumbers));
                    break;

                case "parallel":
                    int threads = Integer.parseInt(args[1]);
                    int[] parNumbers = parseNumbers(args, 2);
                    System.out.println(new Parallel(threads).containsNotPrime(parNumbers));
                    break;

                case "stream":
                    int[] streamNumbers = parseNumbers(args, 1);
                    System.out.println(new ParallelStream().containsNotPrime(streamNumbers));
                    break;

                case "manager":
                    int[] managerNumbers = parseNumbers(args, 1);
                    System.out.println(new Master().processNumbers(managerNumbers));
                    break;

                case "worker":
                    new Slave().start();
                    break;

                case "distributed":
                    String managerHost = args[1];
                    int[] distNumbers = parseNumbers(args, 2);
                    System.out.println(new PrimeDelimeter(managerHost, PORT).containsNotPrime(distNumbers));
                    break;

                default:
                    System.out.println("Unknown mode: " + mode);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static int[] parseNumbers(String[] args, int start) {
        int[] numbers = new int[args.length - start];
        for (int i = start; i < args.length; i++) {
            numbers[i - start] = Integer.parseInt(args[i]);
        }
        return numbers;
    }
}