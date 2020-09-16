public class NBody {

	public static double readRadius(String filename) {
		In in = new In(filename);
		int first = in.readInt();
		double r = in.readDouble();
		return r;
	}

	public static Planet[] readPlanets(String filename) {
		In in = new In(filename);
		int first = in.readInt();
		double second = in.readDouble();
		Planet[] planets = new Planet[5];
		for (int i = 0; i < 5 ; i++) {
			planets[i] = new Planet(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
		}
		return planets;
	}

	public static void main(String[] args) {
		double T = Double.valueOf(args[0].toString());
		double dt = Double.valueOf(args[1].toString());
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);

		String backGround = "./images/starfield.jpg";
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, backGround);
		for (Planet p : planets) {
			p.draw();
		}

		StdDraw.enableDoubleBuffering();
		StdDraw.show();
		StdDraw.pause(10);
		for (double time = 0; time <= T; time += dt) {
			double[] xForces = new double[5];
			double[] yForces = new double[5];
			for (int i = 0; i < 5; i++) {
				Planet p = planets[i];
				xForces[i] = p.calcNetForceExcertedByX(planets);
				yForces[i] = p.calcNetForceExcertedByY(planets);
				p.update(dt, xForces[i], yForces[i]);
			}
			StdDraw.picture(0, 0, backGround);
			for (Planet p : planets) {
			p.draw();
			}
			StdDraw.enableDoubleBuffering();
			StdDraw.show();
			StdDraw.pause(10);

		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    	StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
}

	}

}