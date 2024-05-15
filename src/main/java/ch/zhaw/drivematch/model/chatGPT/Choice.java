package ch.zhaw.drivematch.model.chatGPT;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Choice {
    private int index;
    private Message message;
    private String finish_reason;
}
