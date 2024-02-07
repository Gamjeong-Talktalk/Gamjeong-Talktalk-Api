package com.addi.conversation.presentation;

import java.util.List;

import com.addi.emotion.application.EmotionService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.addi.conversation.appication.VoiceService;
import com.addi.translation.application.PapagoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UploadVoiceController {

	private final PapagoService papagoService;
	private final VoiceService voiceService;

	private final EmotionService emotionService;

	@PostMapping("/api/uploadVoice")
	public ResponseEntity<String> toEmotionalAnalysisResponse(
		@RequestHeader String macAddress,
		@RequestParam("files") List<MultipartFile> files
	) {
		List<String> convertedToTexts = voiceService.convert(macAddress, files);
		System.out.println(convertedToTexts);

		String context = papagoService.translateEngToKor(convertedToTexts);
		//emotionService.EmotionAnalyzeUsingHuggingFace(context);

		return ResponseEntity.ok(context);
	}

}
