package it.unipv.cv.line_detection;

import java.text.MessageFormat;
import java.util.ArrayList;
import it.unipv.cv.utils.Coordinate;

//https://en.wikipedia.org/wiki/Hough_transform

/**
 * 
 * Rapresents the line
 * @author Davide Pio Lotito - Aiman Al Masoud
 * Computer Vision Project - 2022 - UniPV
 *
 */

public class Line{

	/**
	 * Shortest distance of line from origin.
	 */
	final public int rho;

	/**
	 * Angle from x-axis to line in the direction of rho.
	 */
	final public double theta;
	
	
	/**
	 * Slope of the line.
	 */
	final public double slope;

	/**
	 * y-intercept of the line.
	 */
	final public double yintercept;

	public static void main(String[] args) {
		Line.generateThetas(2);
	}
	
	public Line(int rho, double theta) {
		this.rho = rho;
		this.theta  = theta;
		
		//https://aishack.in/tutorials/converting-lines-normal-slopeintercept-form/
		this.slope = -Math.cos(theta)/Math.sin(theta);
		this.yintercept = rho*(1/Math.sin(theta));
	}

	/**
	 * 
	 * Returns a Line that passes through Point p, with chosen value of parameter theta, 
	 * and computed value of parameter rho.
	 * 
	 * Uses this line-parametrization to calculate rho:
	 * rho = x*cos(theta) + y*sin(theta)
	 * 
	 * @param point
	 * @param theta
	 * @return
	 */
	public static Line getLineFor(Coordinate p, double theta) {
		int rho = (int) Math.round((p.X*Math.cos(theta) + p.Y*Math.sin(theta)));
		return new Line(rho, theta);
	}
	
	/**
	 * Like getLineFor but gets a bunch of lines for a predefined set of thetas 
	 * @param p
	 * @return
	 */
	public static ArrayList<Line> getLinesFor(Coordinate p){
		ArrayList<Line> lines = new ArrayList<Line>();
		
		int step = 1;//distance between two adjacent thetas
		Integer[] thetas = Line.generateThetas(step);
		
		for(int theta : thetas){
			lines.add(getLineFor(p, Math.toRadians(theta) ));
		}
		return lines;
	}
	
	
	@Override
	public String toString() {
		return MessageFormat.format("Line(slope={0}, y-intercept={1})", slope, yintercept);
	}
	
	/**
	 * Create a vector of Int, from 0 to 180, euidistanced of step
	 * 
	 * @param step
	 * @return
	 */
	private static Integer[] generateThetas(int step) {
		int size = (180/step);
		Integer[] thetas = new Integer[size];
		for(int i=1; i<size; i++) {
			int value = i * (180) / (size - 1);
			thetas[i]=value;
			System.out.println(value);
		}
		return thetas;
	}
	
	

}
