package com.addi.conversation.appication;

import com.addi.global.exception.BusinessException;

import com.addi.conversation.exception.ConversationError;
import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;
import lombok.RequiredArgsConstructor;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacv.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class VoiceService {

	public List<String> convert(String macAddress, List<MultipartFile> files) {
		if (files.isEmpty()) {
			BusinessException.of(ConversationError.EMPTY_VOICE);
		}

		List<String> transcripts = new ArrayList<>();
		for (MultipartFile file : files) {

			try (SpeechClient speechClient = SpeechClient.create()) {

				File converted = convertAudioToMp3(file);  // 변환 함수 호출

				// 오디오 파일을 byte array로 decode
				byte[] audioBytes = Files.readAllBytes(converted.toPath());  // 변환된 파일 읽기

				// 오디오 객체 생성
				ByteString audioData = ByteString.copyFrom(audioBytes);
				RecognitionAudio recognitionAudio = RecognitionAudio.newBuilder()
						.setContent(audioData)
						.build();

				// 설정 객체 생성
				RecognitionConfig recognitionConfig = RecognitionConfig.newBuilder()
						.setEncoding(RecognitionConfig.AudioEncoding.ENCODING_UNSPECIFIED)
						.setSampleRateHertz(44100)
						.setLanguageCode("ko-KR") // 한국어로 설정
						.build();

				// 오디오-텍스트 변환 수행
				RecognizeResponse response = speechClient.recognize(recognitionConfig, recognitionAudio);
				List<SpeechRecognitionResult> results = response.getResultsList();

				if (!results.isEmpty()) {
					// 주어진 말 뭉치에 대해 여러 가능한 스크립트를 제공. 0번(가장 가능성 있는)을 사용한다.
					SpeechRecognitionResult result = results.get(0);
					transcripts.add(result.getAlternatives(0).getTranscript());
				} else {
					transcripts.add(""); // 빈 문자열을 추가하거나 다른 처리를 수행할 수 있습니다.
				}
			} catch (Exception e) {
				throw new RuntimeException("Error processing audio file: " + file.getOriginalFilename(), e);
			}
		}

		return transcripts;
	}


	private File convertAudioToMp3(MultipartFile multipartFile) throws IOException {
		File targetFile = new File("converted_" + multipartFile.getOriginalFilename() + ".mp3");
		FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(multipartFile.getInputStream());
		try {
			grabber.start();
			FrameRecorder recorder = new FFmpegFrameRecorder(targetFile, 1);
			recorder.setAudioCodec(avcodec.AV_CODEC_ID_MP3);
			recorder.setSampleRate(grabber.getSampleRate());
			recorder.start();

			Frame frame;
			while ((frame = grabber.grabFrame()) != null) {
				recorder.record(frame);
			}

			recorder.stop();
			grabber.stop();
		} catch (FrameGrabber.Exception | FrameRecorder.Exception e) {
			throw new RuntimeException("Error converting audio file: " + multipartFile.getOriginalFilename(), e);
		}
		return targetFile;
	}
}