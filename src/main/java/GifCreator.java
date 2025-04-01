import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GifCreator {

    public static void createGif(List<BufferedImage> frames, String outputPath) throws IOException {
        // Prepare an output file
        File outputFile = new File(outputPath);

        // Create the GIF writer and set parameters
        ImageWriter gifWriter = ImageIO.getImageWritersByFormatName("gif").next();
        ImageWriteParam param = gifWriter.getDefaultWriteParam();
        ImageOutputStream outputStream = ImageIO.createImageOutputStream(outputFile);
        gifWriter.setOutput(outputStream);

        // Start writing the GIF
        gifWriter.prepareWriteSequence(null);

        for (BufferedImage frame : frames) {
            IIOImage iioImage = new IIOImage(frame, null, null);
            gifWriter.writeToSequence(iioImage, param);
        }

        // Finish writing and close the stream
        gifWriter.endWriteSequence();
        outputStream.close();
    }
}

