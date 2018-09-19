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

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc1735.PowerUp2018.Robot;
import org.usfirst.frc1735.PowerUp2018.subsystems.*;

/**
 *
 */
public class AutonomousExperiment extends CommandGroup {


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PARAMETERS
    public AutonomousExperiment() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PARAMETERS
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=COMMAND_DECLARATIONS

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=COMMAND_DECLARATIONS

    	//------------------------------
    	// Command Argument description:
    	// Wait:  Arg is number of seconds
    	// DriveWithPID:  Arg is number of inches.  Negative moves backwards.
    	//                Arg may also be DriveTrain.kUseCamera, which will instead use camera input and object detection to determine distance.
    	// Turn:
    	//       First arg is degrees.  How this is interpreted is defined by next arg
    	//       Second arg is mode:
    	//           DriveTrain.kAbsolute = absolute angle (with 0' defined by last gyro reset).
    	//                                        Positive is clockwise; Negative is counter-clockwise
    	//           DriveTrain.kRelative = Relative to CURRENT robot angle
    	//                                        Positive is clockwise; Negative is counter-clockwise
    	//           DriveTrain.kCamera = Use camera input and object detection to determine angle (first arg for angle is ignored and can be any value)
    	// LightsOn:   No argument; turns the LimeLight camera LEDs on.
    	// LightsOff:  No argument; turns the LimeLight camera LEDs on.
    	// addSequential can take an additional argument for a command "timeout" in seconds.
    	//
    	// Example command:  Turn right by 45 degrees from current position, but time out after 2 seconds even if the robot hasn't completed the turn yet
    	// addSequential(new Turn(45, DriveTrain.kRelative), 2);
    	//--------------------------------

    	addSequential(new ResetGyro()); // Set the current robot position to be zero degrees (for absolute references)
    	addSequential(new Wait(1)); // wait this many seconds before continuing
    	// Drive first leg of the rectangle (side and angle)
    	addSequential(new DriveWithPID(72));
    	addSequential(new Turn(-90, DriveTrain.kAbsolute));
    	
    	// Challenge:  Add additional code to make the robot drive in a 6'x2' rectangle!

    	// Bonus Challenge:  Add additional code to blink the LEDs of the Limelight 3 times (on/off, on/off, on/off) at 1 second intervals
    	
    	// Double Bonus Challenge:  Add additional code to drive the same box, but BACKWARDS.
    	// (See if you end up in the exact same spot you started from!)
    	
    } 
}
