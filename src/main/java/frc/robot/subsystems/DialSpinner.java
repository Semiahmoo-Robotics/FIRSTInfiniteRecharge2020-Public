package frc.robot.subsystems;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.Constants;


public class DialSpinner extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */

    private final ColorSensorV3 m_ColorSensorV3;
    private final Spark m_spinner;

    public DialSpinner() {
        m_ColorSensorV3 = new ColorSensorV3(Constants.COLOR_SENSOR_PORT);
        m_spinner = new Spark(Constants.DIAL_SPIN_MOTOR_PORT);
    }

    public Color getColor() {
      return m_ColorSensorV3.getColor();
    }

    public void startSpin() {
      m_spinner.set(0.3);
    }

    public void stopSpin() {
      m_spinner.set(0);
    }
}