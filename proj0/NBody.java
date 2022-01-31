//import com.sun.xml.internal.messaging.saaj.soap.ver1_1.BodyElement1_1Impl;

import java.lang.reflect.Array;
public class NBody {

    public static double readRadius (String path) {
        In in = new In(path);
        in.readInt();
        return in.readDouble();
    }

    public static Body[] readBodies (String path) {
        In in = new In(path);
        int row = in.readInt();
        double radius = in.readDouble();
        Body[] b = new Body[row];
        for(int i = 0; i < row; i++) {
            double xxP = in.readDouble();
            double yyP = in.readDouble();
            double xxV = in.readDouble();
            double yyV = in.readDouble();
            double m = in.readDouble();
            String iFP = in.readString();
            b[i] = new Body(xxP, yyP, xxV, yyV, m, iFP);
        } return b;
    }

    public static void main (String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Body[] body = readBodies(filename);
        StdDraw.setScale(-radius, radius);
        StdDraw.enableDoubleBuffering();
        double t = 0;
        while (t < T) {
            double[] xForces = new double[body.length];
            double[] yForces = new double[body.length];
            for (int i = 0; i < body.length; i++) {
                xForces[i] = body[i].calcNetForceExertedByX(body);
                yForces[i] = body[i].calcNetForceExertedByY(body);
            }
            for (int i = 0; i < body.length; i++) {
                body[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Body b : body) {
                b.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }
        StdOut.printf("%d\n", body.length);
        StdOut.printf("%.2e\n", radius);
        for (Body value : body) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    value.xxPos, value.yyPos, value.xxVel,
                    value.yyVel, value.mass, value.imgFileName);
        }
    }



}
