package com.addi.member.presentation;

import com.addi.member.application.MemberService;
import com.addi.member.dto.response.LoginToUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@Transactional
@RequiredArgsConstructor
public class MemberSosController {

    private final MemberService memberService;

    // SOS 요청 송신
    @GetMapping("/busan/sasang/sos/{memberId}")
    public ResponseEntity<LoginToUserResponse> sendSOSRequest(@PathVariable Long memberId){
        memberService.sendRequest(memberId);

        return ResponseEntity.ok().build();
    }

    //가사지원
    @GetMapping("/busan/sasang/chores/{memberId}")
    public ResponseEntity<LoginToUserResponse> sendChoreRequest(@PathVariable Long memberId){
        memberService.sendRequest(memberId);

        return ResponseEntity.ok().build();
    }

    //외출동행
    @GetMapping("/busan/sasang/outside/{memberId}")
    public ResponseEntity<LoginToUserResponse> sendOutsideRequest(@PathVariable Long memberId){
        memberService.sendRequest(memberId);

        return ResponseEntity.ok().build();
    }

    //말벗
    @GetMapping("/busan/sasang/friend/{memberId}")
    public ResponseEntity<LoginToUserResponse> sendFriendRequest(@PathVariable Long memberId){
        memberService.sendRequest(memberId);

        return ResponseEntity.ok().build();
    }

    //문화 활동 신청
    @GetMapping("/busan/sasang/culture/{memberId}")
    public ResponseEntity<LoginToUserResponse> sendCultureRequest(@PathVariable Long memberId){
        memberService.sendRequest(memberId);

        return ResponseEntity.ok().build();
    }


}
