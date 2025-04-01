package org.example;

import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;

public class GifWriter {
    private ImageWriter writer;
    private ImageOutputStream output;
    private IIOMetadata metadata;

    public GifWriter(ImageOutputStream output, int imageType, int delay, boolean loop) throws IOException {
        this.output = output;
        Iterator<ImageWriter> writers = ImageIO.getImageWritersBySuffix("gif");
        if (!writers.hasNext()) {
            throw new IllegalStateException("Nenhum escritor GIF encontrado!");
        }
        this.writer = writers.next();
        writer.setOutput(output);
        writer.prepareWriteSequence(null);
        this.metadata = getMetadata(imageType, delay, loop);
    }

    public void writeToSequence(BufferedImage img) throws IOException {
        if (writer == null) {
            throw new IOException("Tentativa de escrever em um GIF já fechado!");
        }
        writer.writeToSequence(new javax.imageio.IIOImage(img, null, metadata), null);
    }


    public void close() throws IOException {
        writer.endWriteSequence();
        output.close();
    }

    private IIOMetadata getMetadata(int imageType, int delay, boolean loop) throws IOException {
        ImageTypeSpecifier typeSpecifier = ImageTypeSpecifier.createFromBufferedImageType(imageType);
        IIOMetadata metadata = writer.getDefaultImageMetadata(typeSpecifier, null);
        String metaFormatName = metadata.getNativeMetadataFormatName();
        IIOMetadataNode root = new IIOMetadataNode(metaFormatName);

        IIOMetadataNode graphicsControlExtensionNode = new IIOMetadataNode("GraphicControlExtension");
        graphicsControlExtensionNode.setAttribute("disposalMethod", "none");
        graphicsControlExtensionNode.setAttribute("userInputFlag", "FALSE");
        graphicsControlExtensionNode.setAttribute("transparentColorFlag", "FALSE");
        graphicsControlExtensionNode.setAttribute("delayTime", Integer.toString(delay / 10)); // 🔄 Mude para:
        graphicsControlExtensionNode.setAttribute("delayTime", Integer.toString(delay));
        graphicsControlExtensionNode.setAttribute("transparentColorIndex", "0");

        IIOMetadataNode applicationExtensionsNode = new IIOMetadataNode("ApplicationExtensions");
        IIOMetadataNode applicationExtensionNode = new IIOMetadataNode("ApplicationExtension");
        applicationExtensionNode.setAttribute("applicationID", "NETSCAPE");
        applicationExtensionNode.setAttribute("authenticationCode", "2.0");
        applicationExtensionNode.setUserObject(new byte[]{0x1, 0, 0});
        applicationExtensionsNode.appendChild(applicationExtensionNode);

        root.appendChild(graphicsControlExtensionNode);
        root.appendChild(applicationExtensionsNode);
        metadata.mergeTree(metaFormatName, root);
        return metadata;
    }
}
