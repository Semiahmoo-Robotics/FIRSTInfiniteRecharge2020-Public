package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSstm;
import frc.robot.Constants;

public class TurnDegreesCmd extends CommandBase {

    private final DriveSstm m_driveSstm;
    private double m_gyroInit;
    private double m_turnDegree;

    public TurnDegreesCmd(DriveSstm driveSstm, double turnDegree) {
        this.m_driveSstm = driveSstm;
        this.m_turnDegree = turnDegree;
        addRequirements(m_driveSstm);
    }

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

    @Override
    public boolean isFinished() {
        if(Math.abs(m_driveSstm.getGyroDeg() - m_gyroInit) > Math.abs(m_turnDegree)) {
            return true;
        }
        return false;
    }

}