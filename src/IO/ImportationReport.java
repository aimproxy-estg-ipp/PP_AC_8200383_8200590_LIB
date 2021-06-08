package IO;

import edu.ma02.io.interfaces.IOStatistics;

import java.time.LocalDateTime;

/*
 * Nome: Micael André Cunha Dias
 * Número: 8200383
 * Turma: LEI1T4
 *
 * Nome: Hugo Henrique Almeida Carvalho
 * Número: 8200590
 * Turma: LEI1T3
 */
public class ImportationReport implements IOStatistics {
    private int nNewMeasurementsRead;
    private int nMeasurementsRead;
    private int nNewStationsRead;
    private int nStationsRead;
    private int nNewSensorsRead;
    private int nSensorsRead;
    private String[] caughtExceptions;
    private int nCaughtExceptions = 0;

    public ImportationReport() {
        caughtExceptions = new String[10];
    }

    private void grow() {
        String[] copy = new String[caughtExceptions.length * 2];
        System.arraycopy(caughtExceptions, 0, copy, 0, caughtExceptions.length);
        caughtExceptions = copy;
    }

    public void addException(StackTraceElement[] stackTrace, String cause) {
        if (nCaughtExceptions == caughtExceptions.length) {
            grow();
        }

        String exceptionMessage = "[" + LocalDateTime.now() + "] [" + stackTrace[0].getClassName() + "] " + cause;

        caughtExceptions[nCaughtExceptions++] = exceptionMessage;
    }

    public void increaseReadStation(boolean newRead) {
        if (newRead) nNewStationsRead++;
        else nStationsRead++;
    }

    public void increaseReadSensor(boolean newRead) {
        if (newRead) nNewSensorsRead++;
        else nSensorsRead++;
    }

    public void increaseReadMeasurement(boolean newRead) {
        if (newRead) nNewMeasurementsRead++;
        else nMeasurementsRead++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumberOfReadMeasurements() {
        return nMeasurementsRead;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumberOfNewStationsRead() {
        return nNewStationsRead;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumberOfStationsRead() {
        return nStationsRead;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumberOfSensorsRead() {
        return nSensorsRead;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumberOfNewSensorsRead() {
        return nNewSensorsRead;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumberOfNewMeasurementsRead() {
        return nNewMeasurementsRead;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getExceptions() {
        if (nCaughtExceptions == 0) return new String[]{};

        return caughtExceptions.clone();
    }
}
