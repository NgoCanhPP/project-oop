import org.opencv.core.Point;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;
import org.opencv.highgui.HighGui;
import org.opencv.objdetect.CascadeClassifier;
public class FaceDetect {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        VideoCapture camera = new VideoCapture(0);
        CascadeClassifier faceDetect = new CascadeClassifier("./lib/haarcascade_frontalface_default.xml");
        MatOfRect faceRectt = new MatOfRect();
        
        camera.set(Videoio.CAP_PROP_FRAME_WIDTH, 600);
        camera.set(Videoio.CAP_PROP_FRAME_HEIGHT, 400);

        Mat img = new Mat();

        while (true) {
            if (!camera.read(img)) {
                System.out.println("Không thể nhận được khung hình");
                break;
            }
            faceDetect.detectMultiScale(img, faceRectt);
            for (Rect rect : faceRectt.toArray()) {
                Imgproc.rectangle(img, new Point(rect.x, rect.y) , new Point(rect.x + rect.width, rect.y + rect.height) , new Scalar(0, 0, 255));
            }
            HighGui.imshow("Camera", img);
            if (HighGui.waitKey(1) == 'q') {
                break;
            }
        }
        
        camera.release();
        HighGui.destroyAllWindows();
    }
}

