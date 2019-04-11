import java.util.List;
import java.util.ArrayList;
public class NBody{
  public static double readRadius(String name)
  {
    In in = new In(name);
    int n = in.readInt();
    double R = in.readDouble();
    return R;
  }

  public static Body[] readBodies(String name)
  {
    In in = new In(name);
    int n = in.readInt();
    double R= in.readDouble();
    Body[] dog = new Body[n];
    while (!in.isEmpty()){
      for (int i=0;i<n;i++)
      {
        double xxPos = in.readDouble();
        double yyPos = in.readDouble();
        double xxVel = in.readDouble();
        double yyVel = in.readDouble();
        double mass = in.readDouble();
        String img = in.readString();
        Body body = new Body(xxPos,yyPos,xxVel,yyVel,mass,img);
        dog[i] = body;
      }
      return dog;
    }
    return dog;
  }

  public static void main(String[] args){
    double T = Double.parseDouble(args[0]);
    double dt= Double.parseDouble(args[1]);
    String filename= args[2];
    Body[] dogs = readBodies(filename);
    double radius= readRadius(filename);

    StdDraw.setScale(-radius, radius);
    StdDraw.picture(0, 0, "images/starfield.jpg");
    for (Body dog : dogs) {
      dog.draw();
    }

    StdDraw.enableDoubleBuffering();
    double time = 0;
    while (time < T) {
      double[] xForces = new double[dogs.length];
      double[] yForces = new double[dogs.length];
      for (int i = 0; i < dogs.length ; i++ ) {
        xForces[i] = dogs[i].calcNetForceExertedByX(dogs);
        yForces[i] = dogs[i].calcNetForceExertedByY(dogs);
      }
      for (int j = 0; j < dogs.length ; j++ ) {
        dogs[j].update(dt, xForces[j], yForces[j]);
      }
      StdDraw.picture(0, 0, "images/starfield.jpg");
      for (Body dog : dogs) {
        dog.draw();
      }
      StdDraw.show();
      StdDraw.pause(10);
      time = time + dt;
    }

    StdOut.printf("%d\n",dogs.length);
    StdOut.printf("%.2e\n",radius);
    for (int i=0;i<dogs.length;i++){
      StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  dogs[i].xxPos, dogs[i].yyPos, dogs[i].xxVel,
                  dogs[i].yyVel, dogs[i].mass, dogs[i].imgFileName);
    }
  }

}
