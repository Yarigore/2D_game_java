package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound() {
        soundURL[0] = getClass().getResource("/sound/cancionPrincipal.wav");
        soundURL[1] = getClass().getResource("/sound/sonidoPuerta.wav");
        soundURL[2] = getClass().getResource("/sound/sonidoLlave.wav");
        soundURL[3] = getClass().getResource("/sound/SwingSword.wav");
        soundURL[4] = getClass().getResource("/sound/hitHurtPlayer.wav");
        soundURL[5] = getClass().getResource("/sound/hitHurtMonster.wav");
    }

    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        }catch (Exception e){
        }
    }

    public void play(){
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clip.stop();
    }
}
