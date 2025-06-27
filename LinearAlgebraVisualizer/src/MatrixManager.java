public class MatrixManager {
    private Matrix2D transformation = Matrix2D.identity();

    public MatrixManager() {
    }

    public void setTransformation(Matrix2D target) {
        transformation = target;
    }

    public Matrix2D getTransformation() {
        return transformation;
    }
}
