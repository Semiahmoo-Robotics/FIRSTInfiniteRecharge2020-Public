/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class SpinnerSstm extends SubsystemBase {

    private final ColorSensorV3 m_ColorSensorV3;
    private final Spark m_spinner;

    public SpinnerSstm() {
        m_ColorSensorV3 = new ColorSensorV3(Constants.COLOR_SENSOR_PORT);
        m_spinner = new Spark(Constants.DIAL_SPINNER_PORT);
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
