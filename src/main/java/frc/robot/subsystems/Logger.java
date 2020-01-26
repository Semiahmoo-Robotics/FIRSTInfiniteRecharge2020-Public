/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * Used to log various data to either to the Smart Dashboard, Shuffle Board,
 * Console.
 */
public class Logger extends SubsystemBase {

    private PowerDistributionPanel m_pdp = new PowerDistributionPanel(Constants.PDP_CAN_PORT);

    public Logger() {
        super();
    }

    /**
     * Reads the data for tx, ty, and ta from the network tables, and logs it on the
     * Smart Dashboard.
     */
    public void limelightLog() {
        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        NetworkTableEntry tx = table.getEntry("tx");
        NetworkTableEntry ty = table.getEntry("ty");
        NetworkTableEntry ta = table.getEntry("ta");

        // read values periodically
        double x = tx.getDouble(0.0);
        double y = ty.getDouble(0.0);
        double area = ta.getDouble(0.0);

        // post to smart dashboard periodically
        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("LimelightArea", area);
    }

    public void robotStatusLog(PowerDistributionPanel pdp) {
        SmartDashboard.putNumber("PDPTemperature", pdp.getTemperature());
        SmartDashboard.putNumber("PDPInputVoltage", pdp.getVoltage());
        SmartDashboard.putNumber("Current Channel 1", pdp.getCurrent(1));
        SmartDashboard.putNumber("Current Channel 1", pdp.getCurrent(1));
    }

    public void gameDataLog() {
        String gameData = DriverStation.getInstance().getGameSpecificMessage();
        switch (gameData.charAt(0)) {
        case 'Y':
            gameData = "Yellow";
            break;
        case 'B':
            gameData = "Blue";
            break;
        case 'R':
            gameData = "Red";
            break;
        case 'G':
            gameData = "Green";
            break;
        }
        SmartDashboard.putString("Position Control Color", gameData);
    }

    @Override
    public void periodic() {
        this.limelightLog();
        this.gameDataLog();
        this.robotStatusLog(m_pdp);
    }

}
