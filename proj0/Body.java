public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double G = 6.67e-11;

    public Body(double xP, double yP, double xV, double yV, double m, String iFN) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = iFN;
    }

    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b) {
        return Math.sqrt((this.xxPos - b.xxPos) * (this.xxPos - b.xxPos) +
                         (this.yyPos - b.yyPos) * (this.yyPos - b.yyPos));
    }

    public double calcForceExertedBy(Body b) {
        return (G * this.mass * b.mass) / Math.pow(calcDistance(b), 2);
    }

    public double calcForceExertedByX(Body b) {
        return calcForceExertedBy(b) * (b.xxPos - this.xxPos) / calcDistance(b);
    }

    public double calcForceExertedByY(Body b) {
        return calcForceExertedBy(b) * (b.yyPos - this.yyPos) / calcDistance(b);
    }

    public double calcNetForceExertedByX(Body[] b) {
        double sum = 0;
        for (Body body : b) {
            if (!this.equals(body)) {
                sum += calcForceExertedByX(body);
            }
        }
        return sum;
    }

    public double calcNetForceExertedByY(Body[] b) {
        double sum = 0;
        for (Body body : b) {
            if (!this.equals(body)) {
                sum += calcForceExertedByY(body);
            }
        }
        return sum;
    }

    public void update(double dt, double xxF, double yyF) {
        double xxa = xxF / this.mass;
        double yya = yyF / this.mass;
        double[] pos = new double[2];
        this.xxVel = this.xxVel + dt * xxa;
        this.yyVel = this.yyVel + dt * yya;
        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
    }

    public void draw () {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }

}
