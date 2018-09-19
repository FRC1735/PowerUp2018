// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1735.PowerUp2018.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc1735.PowerUp2018.Robot;
import org.usfirst.frc1735.PowerUp2018.RobotMap;
import org.usfirst.frc1735.PowerUp2018.subsystems.DriveTrain;

import com.ctre.phoenix.motorcontrol.ControlMode;

/**
 *
 */
public class DriveWithPID extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
    private double m_distance;
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public DriveWithPID(double distance) {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        m_distance = distance;

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        // Init dynamic variable
        m_distHistory = new ArrayList<>();
    }

    //Void constructor gets distance from SmartDashboard.  Allows us to use a button (w/ no preset) to call the command
    public DriveWithPID() {
    	//System.out.println("DriveWithPID Null constructor called");
    	m_getDistFromSmartDashboard = true;
        requires(Robot.driveTrain);
        // Init dynamic variable
        m_distHistory = new ArrayList<>();
   }
    
    // Use this if driving to a cube using the camera
    public DriveWithPID(boolean isWithCamera) {
    	if (isWithCamera) {
    		m_getDistFromCamera = true;
    	}
    	else {
    		// Ummm... punt and pretend this was a null constructor and get data from the SmartDashboard...?\
        	m_getDistFromSmartDashboard = true;
    	}
        requires(Robot.driveTrain);
        // Init dynamic variable
        m_distHistory = new ArrayList<>();
    	
    }
    

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	//Set the driveline's Talons into MotionMagic mode
    	Robot.driveTrain.setPIDMode(); // This also turns off the motors as part of the mode switch.
    	//extract the PID values for this command
    	double p = SmartDashboard.getNumber("P", 0);
    	double i = SmartDashboard.getNumber("I", 0);
    	double d = SmartDashboard.getNumber("D", 0);
    	double f = SmartDashboard.getNumber("F", 0.3789);
    	RobotMap.driveTrainLeftMotor.config_kP(0, p, 0); // Slot, value, timeout in ms
    	RobotMap.driveTrainLeftMotor.config_kI(0, i, 0); // Slot, value, timeout in ms
    	RobotMap.driveTrainLeftMotor.config_kD(0, d, 0); // Slot, value, timeout in ms
    	RobotMap.driveTrainLeftMotor.config_kF(0, f, 0); // Slot, value, timeout in ms
    	RobotMap.driveTrainRightMotor.config_kP(0, p, 0); // Slot, value, timeout in ms
    	RobotMap.driveTrainRightMotor.config_kI(0, i, 0); // Slot, value, timeout in ms
    	RobotMap.driveTrainRightMotor.config_kD(0, d, 0); // Slot, value, timeout in ms
    	RobotMap.driveTrainRightMotor.config_kF(0, f, 0); // Slot, value, timeout in ms
    	
    	// Initialize the desired max speed and acceleration:
    	m_magDir = (int) SmartDashboard.getNumber("Cruise SpeedDir", 2700); // default to full speed
    	RobotMap.driveTrainLeftMotor.configMotionCruiseVelocity(m_magDir, 0);  // speed in encoder units per 100ms
    	RobotMap.driveTrainRightMotor.configMotionCruiseVelocity(m_magDir, 0);
    	
    	m_accel = (int) SmartDashboard.getNumber("Cruise Accel", 5400); //full speed in 1/2 sec (was 8100 = 1/3)
    	int r_accel = (int) SmartDashboard.getNumber("Cruise R Accel", 5400); //was 10000
    	RobotMap.driveTrainLeftMotor.configMotionAcceleration(m_accel, 0); //want xPM in 1 sec, so x/60/10*4096 = 3072 units/100ms
    	RobotMap.driveTrainRightMotor.configMotionAcceleration(r_accel, 0);
    	
    	// If we created this command without args, it should get its distance from the SmartDashboard:
    	// (if not, it was already passed in from the parent CommandGroup and set in the constructor)
    	if (m_getDistFromSmartDashboard) { 
    		m_distance = (int) SmartDashboard.getNumber("Cruise Dist", 18.849); // default:  one revolution of a 6" wheel is just short of 19"
    		System.out.println("Initializing drive distance from SmartDashboard");
    	}
    	
    	// We also can get distance from the camera
    	if (m_getDistFromCamera) {
    		m_distance = (int) Robot.driveTrain.getDistanceToCube();
    		System.out.println("Initializing drive distance from Camera");
    	}

    	//Set a timeout value in seconds
    	setTimeout(4);
    	
    	// initialize a loop iteration counter (used in isFinished(); see below)
    	m_loopCount = 0;
    	    	
    	// Calculate the "encoder distance".  our command input is in something human-readable:  Inches.
    	// But the HW PID controller wants distance in encoder units.
    	m_encDistance = m_distance * DriveTrain.kEncoderTicksPerInch;
    	    	
    	System.out.println("DriveWithPID has been reqeusted for " + m_distance + " inches, or " + m_encDistance + " encoder ticks");
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	// Just update the motor setpoints
    	RobotMap.driveTrainLeftMotor.set(ControlMode./*Position*/MotionMagic, m_encDistance);
    	RobotMap.driveTrainRightMotor.set(ControlMode.MotionMagic, m_encDistance);
    	
    	// Increment the loop count (used in isFinished(); see below)
    	m_loopCount++;
   }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	//  update our rolling average distance traveled.  We need to compare this AVERAGE when deciding when to terminate.
    	double avgDist = calcAvgDist(RobotMap.driveTrainRightMotor.getSelectedSensorPosition(0));

    	System.out.println(" FLerr: " + RobotMap.driveTrainLeftMotor.getClosedLoopError(0) +
    			" out_pct: " + RobotMap.driveTrainLeftMotor.getMotorOutputPercent() +
    			" CLTarget: " + RobotMap.driveTrainLeftMotor.getClosedLoopTarget(0) +
    			" Pos: " + RobotMap.driveTrainLeftMotor.getSelectedSensorPosition(0) +
    			" Vel: " + RobotMap.driveTrainLeftMotor.getSelectedSensorVelocity(0) + 
    			" Mode: " + RobotMap.driveTrainLeftMotor.getControlMode() + 
    			" AvgDist: " + avgDist + "\n" +
    			" FRerr: " + RobotMap.driveTrainRightMotor.getClosedLoopError(0) +
    			" out_pct: " + RobotMap.driveTrainRightMotor.getMotorOutputPercent() +
    			" CLTarget: " + RobotMap.driveTrainRightMotor.getClosedLoopTarget(0) +
    			" Pos: " + RobotMap.driveTrainRightMotor.getSelectedSensorPosition(0) +
    			" Vel: " + RobotMap.driveTrainRightMotor.getSelectedSensorVelocity(0) +
    			" Mode: " + RobotMap.driveTrainLeftMotor.getControlMode());
  				
    	//@FIXME:  Should finished be checking BOTH sensors??
    	
    	// The most intuitive thing to check would be the closed loop error, and if it's less than the allowable error we're done.
    	// However, the first ~5 iterations (@20ms, this is about 100ms) don't report accurate CLerr, so we'll avoid that and instead check if our sensor position is within the allowed error of the setpoint.
    	// Unfortunately, the first iteration of the command hasn't yet actually seen the zeroed out sensor and will see whatever position was present prior to starting this command.
    	// So, we need to skip checking anything on the first iteration.
    	boolean distReached = (Math.abs(avgDist - m_encDistance) < DriveTrain.kToleranceDistUnits);
    	
    	if (m_loopCount > 1) //The first execute will inc to 1, so the first isFinished will see 1 as well.  this is the iteration we want to skip.
    		return distReached || isTimedOut();
    	else
    		return false; // On the first iteration, don't terminate (we have no valid data upon which to calculate a termination value!)
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    	Robot.driveTrain.setOpenLoopMode(); // This also turns off the motors as part of the mode switch
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    	end();
    }
    
    // Given a new distance, calculate a new rolling average.
    protected double calcAvgDist(int latestDist) {
    	// Remove the oldest item in the distHistory (if too many exist)
    	while (m_distHistory.size() > 50) // Rolling average of n items 0..(n-1)
    		m_distHistory.remove(0);
    	
    	// Add the latest distance to the list
    	m_distHistory.add(latestDist);
    	
    	// Calculate the new average
    	double newSum =  0;
    	for (int i=0; i< m_distHistory.size(); i++) {
    		newSum = newSum + m_distHistory.get(i);
    	}

    	double newAvg = newSum / m_distHistory.size();
    	return newAvg;
    }
    
    // Member Variables
    int m_magDir;
    int m_accel;
    boolean m_getDistFromSmartDashboard = false;
    boolean m_getDistFromCamera = false;
    double m_loopCount;
    double m_encDistance; // This is the requested distance in encoder ticks, as opposed to m_distance which is in inches.
    private List<Integer> m_distHistory;  // Holds a history of previous sensor distance values

}
