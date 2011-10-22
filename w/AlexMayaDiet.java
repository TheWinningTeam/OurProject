import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class AlexMayaDiet {
    public static void main(String[] args) {
        try {
            if (InetAddress.getLocalHost().getHostName()
                .equals("nom26779d.nomadic.ncsu.edu")) {
                try {
                    System.setIn(new FileInputStream(new File(
                    "/Users/ricky/Documents/IEEEXtreme/problemC/input.txt")));
                } catch (FileNotFoundException e1) {
                    System.out.println("Input file not found");
                    System.exit(1);
                }
            }
        } catch (UnknownHostException e1) {
        }

        InputStreamReader converter = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(converter);

        // begin your stuff here

        String line;
        double[] desired = new double[3];

        final int d;
        try {
            line = in.readLine();
            String[] argList = line.split(" ");
            desired[0] = Double.parseDouble(argList[0]);
            desired[1] = Double.parseDouble(argList[1]);
            desired[2] = Double.parseDouble(argList[2]);
        } catch (IOException e) {
            System.exit(1);
            return; // stupid compiler
        }

        try {
            in.readLine();
            d = Integer.parseInt(in.readLine());
        } catch (IOException e) {
            System.exit(1);
            return; // stupid compiler
        }

        double[][] foodMatrix = new double[5][d];
        String[] label = new String[d];

        int lblidx = 0;
        try {
            while((line = in.readLine()) != null) {
                String[] argList = line.split(" ");
                label[lblidx] = argList[0];
                foodMatrix[0][lblidx] = Double.parseDouble(argList[1]);
                foodMatrix[1][lblidx] = Double.parseDouble(argList[2]);
                foodMatrix[2][lblidx] = Double.parseDouble(argList[3]);
                foodMatrix[3][lblidx] = Double.parseDouble(argList[4]);
                lblidx++;
            }
        } catch (IOException e) {
        }

        foodMatrix[0][d-1] = desired[0];
        foodMatrix[1][d-1] = desired[0];
        foodMatrix[2][d-1] = desired[0];
        foodMatrix[3][d-1] = 0.0;

        for(int i=0; i<foodMatrix.length; i++) {
            System.out.println(foodMatrix[i][0]+","+foodMatrix[i][1]+","+ foodMatrix[i][2]+","+foodMatrix[i][3]+","+foodMatrix[i][4]);
        }
    }

    private static class GaussianElimination {
        private static final double EPSILON = 1e-10;

        // Gaussian elimination with partial pivoting
        public static double[] lsolve(double[][] A, double[] b) {
            int N  = b.length;

            for (int p = 0; p < N; p++) {

                // find pivot row and swap
                int max = p;
                for (int i = p + 1; i < N; i++) {
                    if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
                        max = i;
                    }
                }
                double[] temp = A[p]; A[p] = A[max]; A[max] = temp;
                double   t    = b[p]; b[p] = b[max]; b[max] = t;

                // singular or nearly singular
                if (Math.abs(A[p][p]) <= EPSILON) {
                    throw new RuntimeException("Matrix is singular or nearly singular");
                }

                // pivot within A and b
                for (int i = p + 1; i < N; i++) {
                    double alpha = A[i][p] / A[p][p];
                    b[i] -= alpha * b[p];
                    for (int j = p; j < N; j++) {
                        A[i][j] -= alpha * A[p][j];
                    }
                }
            }

            // back substitution
            double[] x = new double[N];
            for (int i = N - 1; i >= 0; i--) {
                double sum = 0.0;
                for (int j = i + 1; j < N; j++) {
                    sum += A[i][j] * x[j];
                }
                x[i] = (b[i] - sum) / A[i][i];
            }
            return x;
        }


        // sample client
        public static void main(String[] args) {
            int N = 3;
            double[][] A = { { 0, 1,  1 },
                            { 2, 4, -2 },
                            { 0, 3, 15 }
                        };
            double[] b = { 4, 2, 36 };
            double[] x = lsolve(A, b);


            // print results
            for (int i = 0; i < N; i++) {
                System.out.println(x[i]);
            }

        }

    }

}
