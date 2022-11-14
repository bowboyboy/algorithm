public class NBody {
     public static double readRadius(String file){
         In in = new In(file);
         int num = in.readInt();
         double redius = in.readDouble();
         return redius;

     }
     public static Planet[] readPlanets(String file){
         In in = new In(file);
         int num = in.readInt();
         in.readDouble();
         Planet[] allPlanet = new Planet[num];
         int cnt = 0;
         while(cnt < num){
             double xP = in.readDouble();
             double yP = in.readDouble();
             double vX = in.readDouble();
             double vY = in.readDouble();
             double ma = in.readDouble();
             String img = in.readString();
             allPlanet[cnt] = new Planet(xP, yP, vX, vY, ma, img);
             cnt += 1;
         }
         return allPlanet;
     }

    public static String imageToDraw = "images/starfield.jpg";
     public static void main(String [] args){
         double T = Double.parseDouble(args[0]);
         double dt = Double.parseDouble(args[1]);
         String fileName = args[2];
         double radius = readRadius(fileName);
         Planet[] allPlanets = readPlanets(fileName);

         StdDraw.setScale(-radius,radius);
         StdDraw.enableDoubleBuffering();
         double t = 0;
         while (t < T){
             Double[] xForces = new Double[allPlanets.length];
             Double[] yForces = new Double[allPlanets.length];
             for(int i=0;i<allPlanets.length;i++){
                 xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
                 yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
             }
             for(int i=0;i<allPlanets.length;i++){
                 allPlanets[i].update(dt,xForces[i],yForces[i]);
             }
             StdDraw.picture(0,0,imageToDraw);
             for (Planet s :allPlanets)
             {
                 s.draw();
             }
             StdDraw.show();
             StdDraw.pause(200);
             t += dt;
          }
         StdOut.printf("%d\n",allPlanets.length);
         StdOut.printf("%.2e\n",radius);
         for(int i=0;i< allPlanets.length;i++){
             StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",allPlanets[i].xxPos,allPlanets[i].yyPos
             ,allPlanets[i].xxVel,allPlanets[i].yyVel,allPlanets[i].mass,allPlanets[i].imgFileName);
         }
     }
}
