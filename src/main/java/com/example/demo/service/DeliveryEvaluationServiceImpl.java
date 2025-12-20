package com.example.demo.service;

import com.example.demo.entity.DeliveryEvaluation;
import com.example.demo.entity.SLARequirement;
import com.example.demo.entity.Vendor;
import com.example.demo.repository.DeliveryEvaluationRepository;
import com.example.demo.repository.SLARequirementRepository;
import com.example.demo.repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DeliveryEvaluationServiceImpl implements DeliveryEvaluationService {

	private final DeliveryEvaluationRepository evaluationRepository;
	private final VendorRepository vendorRepository;
	private final SLARequirementRepository slaRepository;

	public DeliveryEvaluationServiceImpl(DeliveryEvaluationRepository evaluationRepository,
	                                     VendorRepository vendorRepository,
	                                     SLARequirementRepository slaRepository) {
		this.evaluationRepository = evaluationRepository;
		this.vendorRepository = vendorRepository;
		this.slaRepository = slaRepository;
	}

	@Override
	public DeliveryEvaluation createEvaluation(DeliveryEvaluation evaluation) {

		Vendor vendor = vendorRepository.findById(evaluation.getVendor().getId())
		                .orElseThrow(() -> new IllegalArgumentException("Vendor not found"));
		if (!vendor.getActive()) throw new IllegalStateException("Vendor is not active");

		SLARequirement sla = slaRepository.findById(evaluation.getSlaRequirement().getId())
		                     .orElseThrow(() -> new IllegalArgumentException("SLA Requirement not found"));
		if (!sla.getActive()) throw new IllegalStateException("SLA Requirement is not active");

		if (evaluation.getActualDeliveryDays() == null || evaluation.getActualDeliveryDays() < 0)
			throw new IllegalArgumentException("Actual delivery days must be >= 0");

		if (evaluation.getQualityScore() == null || evaluation.getQualityScore() < 0 || evaluation.getQualityScore() > 100)
			throw new IllegalArgumentException("Quality score must be between 0 and 100");

		evaluation.setVendor(vendor);
		evaluation.setSlaRequirement(sla);

		if (evaluation.getEvaluationDate() == null) evaluation.setEvaluationDate(LocalDate.now());

		evaluation.setMeetsDeliveryTarget(evaluation.getActualDeliveryDays() <= sla.getMaxDeliveryDays());
		evaluation.setMeetsQualityTarget(evaluation.getQualityScore() >= sla.getMinQualityScore());

		return evaluationRepository.save(evaluation);
	}

	@Override
	public DeliveryEvaluation getEvaluationById(Long id) {
		return evaluationRepository.findById(id)
		       .orElseThrow(() -> new IllegalArgumentException("Evaluation not found"));
	}

	@Override
	public List<DeliveryEvaluation> getEvaluationsForVendor(Long vendorId) {
		return evaluationRepository.findByVendorId(vendorId);
	}

	@Override
	public List<DeliveryEvaluation> getEvaluationsForRequirement(Long requirementId) {
		return evaluationRepository.findBySlaRequirementId(requirementId);
	}
}
