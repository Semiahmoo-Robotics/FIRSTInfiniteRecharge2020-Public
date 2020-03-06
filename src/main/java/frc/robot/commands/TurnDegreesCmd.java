package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSstm;
import frc.robot.Constants;

public class TurnDegreesCmd extends CommandBase {

    private final DriveSstm m_driveSstm;
    private double m_gyroInit;
    private double m_turnDegree;

    /**
     * 
     * @param driveSstm
     * @param turnDegree this is the customizable magnitude of turning.
     *        Positive for clockwise, negative for counter-clockwise.
     */
    public TurnDegreesCmd(DriveSstm driveSstm, double turnDegree) {
        this.m_driveSstm = driveSstm;
        this.m_turnDegree = turnDegree;
        addRequirements(m_driveSstm);
    }

    /**
     * This method deferentiates between positive and negative rotation.
     * 
     * AUTO_ROTATION_SPEED is my suggestion for a new constant moderating the speed during turns.
     */
    @Override
    public void initialize() {
        m_gyroInit = m_driveSstm.getGyroDeg();
        if(m_turnDegree > 0) {
            m_driveSstm.tankDrive(Constants.AUTO_ROTATION_SPEED, - Constants.AUTO_ROTATION_SPEED);
        } else if (m_turnDegree < 0) {
            m_driveSstm.tankDrive(- Constants.AUTO_ROTATION_SPEED, Constants.AUTO_ROTATION_SPEED);
        }
    }

    @Override
    public void end(boolean interrupted) {
        m_driveSstm.tankDrive(0, 0);
    }

    /**
     * This method checks if the turn has ended by:
     *  1. Finding how much the robot has turned
     *  2. See if the absolute value of completed turn is bigger than the absolute value of desired turn
     */
    @Override
    public boolean isFinished() {
        if(Math.abs(m_driveSstm.getGyroDeg() - m_gyroInit) > Math.abs(m_turnDegree)) {
            return true;
        }
        return false;
    }

}