package ffleitas.back.service.impl;

import ffleitas.back.domain.repositories.MedidaRepository;
import ffleitas.back.dtos.medidas.MedidaDTO;
import ffleitas.back.exceptions.ElementoInexistenteException;
import ffleitas.back.mappers.MedidaMapper;
import ffleitas.back.service.MedidasService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
@Getter
public class MedidasServiceImpl implements MedidasService
{

	private final MedidaRepository medidasRepository;

	@Override
	public List<MedidaDTO> listarMedidas()
	{
		final var listadoMedidas = medidasRepository.findAllNotDeteled();
		if (listadoMedidas.isEmpty()) {
			throw new ElementoInexistenteException("No se encontraron medidas para la busqueda");
		}
		return MedidaMapper.toMedidaDTOList(listadoMedidas);
	}
}
