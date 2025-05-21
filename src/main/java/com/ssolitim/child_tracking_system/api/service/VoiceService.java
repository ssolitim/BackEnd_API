package com.ssolitim.child_tracking_system.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssolitim.child_tracking_system.api.dto.voice.VoiceResponse;
import com.ssolitim.child_tracking_system.api.model.Voice;
import com.ssolitim.child_tracking_system.api.repository.VoiceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VoiceService {

    private final VoiceRepository voiceRepository;

    public List<VoiceResponse> registerVoice(String content) {
        Voice voice = Voice.builder()
            .content(content)
            .build();
        voiceRepository.save(voice);

        return voiceRepository.findAll().stream()
            .map(VoiceResponse::from)
            .toList();
    }

    public List<VoiceResponse> getVoice() {
        List<Voice> voices = voiceRepository.findAll();
        return voices.stream()
            .map(VoiceResponse::from)
            .toList();
    }

    public List<VoiceResponse> deleteVoice(Integer voiceId) {
        voiceRepository.deleteById(voiceId);
        List<Voice> voices = voiceRepository.findAll();
        return voices.stream()
            .map(VoiceResponse::from)
            .toList();
    }

    public List<VoiceResponse> checkVoice(Integer voiceId) {
        Voice ChangedVoice = voiceRepository.findById(voiceId)
            .orElseThrow(() -> new IllegalArgumentException("음성을 찾을 수 없습니다."));

        // 기존 음성들 체크 해제
        List<Voice> voices = voiceRepository.findAll();
        voices.forEach(Voice::uncheck);

        ChangedVoice.check();
        return voiceRepository.findAll().stream()
            .map(VoiceResponse::from)
            .toList();
    }
}
