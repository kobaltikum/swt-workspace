package org.iMage.geometrify;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

public class TrianglePictureFilter extends AbstractPrimitivePictureFilter {

  public TrianglePictureFilter(IPointGenerator pointGenerator) {
    super(pointGenerator);
  }

  @Override
  protected Color calculateColor(BufferedImage image, IPrimitive primitive) {
    BoundingBox b = primitive.getBoundingBox();
    ArrayList<Integer> allValues = new ArrayList<Integer>();
    int alpha = 0;
    int red = 0;
    int green = 0;
    int blue = 0;
    int numberOfValues = 1;
    Objects.requireNonNull(b.getLowerRightCorner()); //TODO weg
    for (int i = b.getUpperLeftCorner().y; i <= b.getLowerRightCorner().y; i++) {
      
      for (int j = b.getUpperLeftCorner().x; j <= b.getLowerRightCorner().x; j++) {
        if (primitive.isInsidePrimitive(new Point(j, i))) {
          int rgbValue = image.getRGB(j, i);
          allValues.add(rgbValue);
          numberOfValues++;
        }
      }
    }
    
    for (int l = 0; l < allValues.size(); l++) { //All values are added separately...
      alpha += (allValues.get(l) >> 24) & 0xFF;
      red += (allValues.get(l) >> 16) & 0xFF;
      green += (allValues.get(l) >> 8) & 0xFF;
      blue += (allValues.get(l)) & 0xFF;
    }

    Color averageColor = new Color((red / (numberOfValues)), //... and the average values
        (green / (numberOfValues)),                          //are created.
        (blue / (numberOfValues)),
        (alpha / (numberOfValues)));

    return averageColor;
  }

  @Override
  public BufferedImage apply(BufferedImage image, int numberOfIterations, int numberOfSamples) {
    
    if ((numberOfIterations == 0) || (numberOfSamples == 0)) { 
      return image;
      
    } else {
      BufferedImage wipImage = new BufferedImage(image.getWidth(),
          image.getHeight(),
          image.getType());

      for (int i = 0; i < numberOfIterations; i++) {
        Triangle tempTriangle = new Triangle(new Point(1, 0), // Initialized
            new Point(0, 0), // only for compilation purposes.
            new Point(0, 1));
        int currMinDiff = 0;
        boolean first = true;
        
        for (int j = 0; j < numberOfSamples; j++) {
          Triangle triangle = this.generatePrimitive();
          triangle.setColor(this.calculateColor(image, triangle));
          if (first) { // First triangle on similarity test
            currMinDiff = this.calculateDifference(image, wipImage, triangle); 
            tempTriangle = triangle;
            first = false;
          } else {
            if (currMinDiff > this.calculateDifference(image, wipImage, triangle)) {
              currMinDiff = this.calculateDifference(image, wipImage, triangle);
              tempTriangle = triangle;
            }
          }
        }
        this.addToImage(wipImage, tempTriangle);
        
      }
      
      return wipImage;
    }
  }

  @Override
  protected Triangle generatePrimitive() {
    Point a = this.pointGenerator.nextPoint();
    Objects.requireNonNull(a);
    Point b = this.pointGenerator.nextPoint();
    Objects.requireNonNull(b);
    Point c = this.pointGenerator.nextPoint();
    Objects.requireNonNull(c);
    Triangle t = new Triangle(a, b, c);
    Objects.requireNonNull(t);
    return t;
  }

  @Override
  protected int calculateDifference(BufferedImage origin, BufferedImage curr, IPrimitive prim) {
    Triangle t = (Triangle) prim;
    BoundingBox b = t.getBoundingBox();
    this.addToImage(curr, prim);
    int diff = 0;
    Objects.requireNonNull(b.getLowerRightCorner()); //TODO weg
    for (int i = b.getUpperLeftCorner().y; i <= b.getLowerRightCorner().y; i++) {
      
      for (int j = b.getUpperLeftCorner().x; j <= b.getLowerRightCorner().x; j++) {
        int originRgb = origin.getRGB(j, i);
        /*int orgA = (originRgb >> 24) & 0xFF; // old alpha value
        int orgR = (originRgb >> 16) & 0xFF; // old red value
        int orgG = (originRgb >> 8) & 0xFF; // old green value
        int orgB = (originRgb) & 0xFF; // old blue value */
        int currRgb = curr.getRGB(j, i);
        /*int currA = (currRgb >> 24) & 0xFF; // current alpha value
        int currR = (currRgb >> 16) & 0xFF; // current red value
        int currG = (currRgb >> 8) & 0xFF; // current green value
        int currB = (currRgb) & 0xFF; // current blue value */
        /*diff += Math.abs(orgA - currA)
            + Math.abs(orgR - currR)
            + Math.abs(orgG - currG)
            + Math.abs(orgB - currB); */
        diff += Math.abs(originRgb - currRgb);
      }
    }
    return diff;
  }

  @Override
  protected void addToImage(BufferedImage image, IPrimitive prim) {
    BoundingBox boundingBox = prim.getBoundingBox();
    Color primitiveColor;
    for (int i = boundingBox.getUpperLeftCorner().y;
        i <= boundingBox.getLowerRightCorner().y; i++) {
      
      for (int j = boundingBox.getUpperLeftCorner().x;
          j <= boundingBox.getLowerRightCorner().x; j++) {
        if (prim.isInsidePrimitive(new Point(j, i))) {
          int imageRgb = image.getRGB(j, i);
          int newAlpha = (((imageRgb >> 24) & 0xFF) + prim.getColor().getAlpha()) / 2;
          int newRed = (((imageRgb >> 16) & 0xFF) + prim.getColor().getRed()) / 2;
          int newGreen = (((imageRgb >> 8) & 0xFF) + prim.getColor().getGreen()) / 2;
          int newBlue = (((imageRgb) & 0xFF) + prim.getColor().getBlue()) / 2;
          primitiveColor = new Color(newRed, newGreen, newBlue, newAlpha);
          image.setRGB(j, i, primitiveColor.getRGB()); //The average color is painted on the image.
        }
      }
    }
  }
}
