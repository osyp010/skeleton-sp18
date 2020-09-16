public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	static final double constG = 6.67 * 1.0e-11;

	public Planet(double xP, double yP, double xV,
              double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	public  double calcDistance(Planet p) {
		double dx = p.xxPos - this.xxPos;
		double dy = p.yyPos - this.yyPos;
		return Math.sqrt(dx*dx + dy*dy);
	}

	public double calcForceExertedBy(Planet p) {
		double r = this.calcDistance(p);
		double force = constG * this.mass * p.mass / (r * r);
		return force;
	}

	public double calcForceExertedByX(Planet p) {
		double r = this.calcDistance(p);
		double force = this.calcForceExertedBy(p);
		double dx = p.xxPos - this.xxPos;
		return force * dx / r;
	}

	public double calcForceExertedByY(Planet p) {
		double r = this.calcDistance(p);
		double force = this.calcForceExertedBy(p);
		double dy = p.yyPos - this.yyPos;
		return force * dy / r;
	}

	public double calcNetForceExcertedByX(Planet[] planets) {
		double netForce = 0.0;
		for (Planet p : planets) {
			if (p.equals(this)) {
				continue;
			}
			netForce += this.calcForceExertedByX(p);
		}
		return netForce;
	}

	public double calcNetForceExcertedByY(Planet[] planets) {
		double netForce = 0.0;
		for (Planet p : planets) {
			if (p.equals(this)) {
				continue;
			}
			netForce += this.calcForceExertedByY(p);
		}
		return netForce;
	}

	public void update(double dt, double fX, double fY) {
		double aX = fX / this.mass;
		double aY = fY / this.mass;
		this.xxVel = this.xxVel + dt * aX;
		this.yyVel = this.yyVel + dt * aY;
		this.xxPos = this.xxPos + dt * xxVel;
		this.yyPos = this.yyPos + dt * yyVel;
	}

	public void draw() {
		String path = "./images/" + imgFileName;
		StdDraw.picture(xxPos, yyPos, path);	
	}

}