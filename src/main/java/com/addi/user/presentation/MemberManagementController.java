package com.addi.user.presentation;

import com.addi.user.application.ManagementService;
import com.addi.user.dto.response.GetMembersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Transactional
@RequiredArgsConstructor
public class MemberManagementController {

    private final ManagementService managementService;

    //보호자관리 대상 유저 정보
    @GetMapping("/api/members/{guardianId}")
    public ResponseEntity<List<GetMembersResponse>> getMembers(@PathVariable Long guardianId){
        List<GetMembersResponse> membersResponses = managementService.getMembmers(guardianId);

        return ResponseEntity.ok(membersResponses);
    }

    // SOS 요청 송신
    @GetMapping("/api/request/sos/{memberId}")
    public void sendSOSRequest(@PathVariable Long memberId){
        managementService.sendRequest(memberId);
    }

    //가사지원
    @GetMapping("/api/request/chores/{memberId}")
    public void sendChoreRequest(@PathVariable Long memberId){
        managementService.sendRequest(memberId);
    }

    //외출동행
    @GetMapping("/api/request/outside/{memberId}")
    public void sendOutsideRequest(@PathVariable Long memberId){
        managementService.sendRequest(memberId);
    }

    //말벗
    @GetMapping("/api/request/friend/{memberId}")
    public void sendFriendRequest(@PathVariable Long memberId){
        managementService.sendRequest(memberId);
    }

    //문화 활동 신청
    @GetMapping("/api/request/culture/{memberId}")
    public void sendCultureRequest(@PathVariable Long memberId){
        managementService.sendRequest(memberId);
    }
}
