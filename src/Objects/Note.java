package Objects;

public class Note {

    private int pitchClass;
    private int octave;
    private int duration;

    public void setPitchClass(int pitchClass) { this.pitchClass = pitchClass; }

    public void setOctave(int octave) { this.octave = octave; }

    public void setDuration(int duration) { this.duration = duration; }

    public int getOctave() { return octave; }

    public int getPitchClass() { return pitchClass; }

    public int getDuration() { return duration; }
}
