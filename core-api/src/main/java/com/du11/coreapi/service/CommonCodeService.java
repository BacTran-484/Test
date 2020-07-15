package com.du11.coreapi.service;

import com.du11.coreapi.dto.response.SelectBoxResponseDTO;

import java.util.List;

public interface CommonCodeService {

    List<SelectBoxResponseDTO> getMainCodeForSelectbox();
}
