package UseCases;
import org.ghost4j.document.Document;
import org.ghost4j.document.DocumentException;
import org.ghost4j.document.PDFDocument;
import org.ghost4j.renderer.RendererException;
import org.ghost4j.renderer.SimpleRenderer;
import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.util.List;
import javax.imageio.ImageIO;
import java.io.IOException;

// Use ghost j4 library for this for this, from maven -> org.ghost4j:ghost4j:1.0.1

public class PDFtoImage {
    /**
     * Saves an image of a given pdf
     * @param pdfFilePath file path for the pdf
     * @param imExtension desired image extension
     * @throws IOException Input output error exception
     * @throws DocumentException Document exception error
     * @throws RendererException Rendering the image exception
     */
    public void convertPdfToIm( String pdfFilePath, String imExtension ) throws
            IOException,DocumentException,RendererException{
    // load the pdf
        document.load( new File( pdfFilePath ) );

    // create renderer
    SimpleRenderer renderer = new SimpleRenderer();

    // set resolution (in DPI)
        renderer.setResolution( 300 );

    // render the images
    List<Image> images = renderer.render( document );

    // write the images to file
        for (int iPage = 0; iPage < images.size(); iPage++) {
        ImageIO.write( (RenderedImage) images.get( iPage ), imExtension,
                new File( "" + iPage + "." + imExtension ) );
    }

}

}