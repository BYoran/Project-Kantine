public class Main {

    public Main() {

    }

    public static void main(String[] args) {
        int[] intArray = new int[]{ -10, 10, 2, 4 };
        System.out.println(Administratie.berekenGemiddeldAantal(intArray));
        double[] doubleArray = new double[]{ -3.2, 3.2, 1.2, 3.8 };
        System.out.println(Administratie.berekenGemiddeldeOmzet(doubleArray));
        double[] dagTotalen = new double[]{321.35, 450.50, 210.45, 190.85, 193.25, 159.90, 214.25, 220.90, 201.90, 242.70, 260.35};
        System.out.println(Administratie.berekenDagOmzet(dagTotalen));
    }
}