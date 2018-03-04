// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1735.PowerUp2018;

import org.usfirst.frc1735.PowerUp2018.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public JoystickButton tankMode;
    public JoystickButton arcadeMode;
    public Joystick joyLeft;
    public Joystick joyRight;
    public JoystickButton deployClamps;
    public JoystickButton retractClamps;
    public JoystickButton clampsClose;
    public JoystickButton clampsOpen;
    public JoystickButton piggybackDeploy;
    public JoystickButton piggybackRetract;
    public JoystickButton extendHook;
    public JoystickButton retractHook;
    public Joystick operator;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        operator = new Joystick(2);
        
        retractHook = new JoystickButton(operator, 7);
        retractHook.whenPressed(new HookRetract());
        extendHook = new JoystickButton(operator, 6);
        extendHook.whenPressed(new HookExtend());
        piggybackRetract = new JoystickButton(operator, 8);
        piggybackRetract.whileHeld(new RetractPiggyback());
        piggybackDeploy = new JoystickButton(operator, 9);
        piggybackDeploy.whileHeld(new DeployPiggyback());
        clampsOpen = new JoystickButton(operator, 2);
        clampsOpen.whenPressed(new OpenClamps());
        clampsClose = new JoystickButton(operator, 1);
        clampsClose.whenPressed(new CloseClamps());
        retractClamps = new JoystickButton(operator, 4);
        retractClamps.whileHeld(new MoveClamps(-1));
        deployClamps = new JoystickButton(operator, 5);
        deployClamps.whileHeld(new MoveClamps(1));
        joyRight = new Joystick(1);
        
        joyLeft = new Joystick(0);
        
        arcadeMode = new JoystickButton(joyLeft, 2);
        arcadeMode.whenPressed(new EnterArcadeMode());
        tankMode = new JoystickButton(joyLeft, 1);
        tankMode.whenPressed(new EnterTankMode());


        // SmartDashboard Buttons
        SmartDashboard.putData("Auto RRR", new AutoRRR());
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("Turn: absZero", new Turn(0, 0));
        SmartDashboard.putData("Turn: abs90", new Turn(90, 0));
        SmartDashboard.putData("Turn: cube", new Turn(0, 2));
        SmartDashboard.putData("AutonomousExperiment", new AutonomousExperiment());
        SmartDashboard.putData("ResetGyro", new ResetGyro());
        SmartDashboard.putData("Turn To Cube", new TurnToCube());
        SmartDashboard.putData("DriveWithPID", new DriveWithPID());
        SmartDashboard.putData("ElevatorwithPID: load ", new ElevatorwithPID(0));
        SmartDashboard.putData("ElevatorwithPID: switch", new ElevatorwithPID(24));
        SmartDashboard.putData("ElevatorwithPID: scale", new ElevatorwithPID(66));
        SmartDashboard.putData("ElevatorwithPID: climb", new ElevatorwithPID(84));
        SmartDashboard.putData("HookExtend", new HookExtend());
        SmartDashboard.putData("HookRetract", new HookRetract());
        SmartDashboard.putData("AutoSwitchAndScale", new AutoSwitchAndScale());
        SmartDashboard.putData("AutoSwitch2x", new AutoSwitch2x());
        SmartDashboard.putData("AutoScale2x", new AutoScale2x());
        SmartDashboard.putData("AutoLineOnly", new AutoLineOnly());
        SmartDashboard.putData("AutoSwitch1x", new AutoSwitch1x());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // Add some more Commands with a null argument.  these will grab data from the SmartDashboard.
        SmartDashboard.putData("ElevatorwithPID: FromSD", new ElevatorwithPID());
        SmartDashboard.putData("Turn: FromSD", new Turn()); 
        SmartDashboard.putData("DriveToCube", new DriveWithPID(true)); // Drive using the camera-calculated distance to the cube
        
        
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getJoyLeft() {
        return joyLeft;
    }

    public Joystick getJoyRight() {
        return joyRight;
    }

    public Joystick getOperator() {
        return operator;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}

