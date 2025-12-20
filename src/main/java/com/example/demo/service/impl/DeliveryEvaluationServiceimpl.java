@Service
public class DeliveryEvaluationServiceImpl implements DeliveryEvaluationService {

    private final DeliveryEvaluationRepository evaluationRepository;
    private final VendorRepository vendorRepository;
    private final SLARequirementRepository requirementRepository;

    public DeliveryEvaluationServiceImpl(
            DeliveryEvaluationRepository evaluationRepository,
            VendorRepository vendorRepository,
            SLARequirementRepository requirementRepository) {

        this.evaluationRepository = evaluationRepository;
        this.vendorRepository = vendorRepository;
        this.requirementRepository = requirementRepository;
    }

    @Override
    public DeliveryEvaluation createEvaluation(DeliveryEvaluation evaluation) {

        Vendor vendor = vendorRepository.findById(evaluation.getVendor().getId())
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        if (!vendor.isActive()) {
            throw new ValidationException("active vendors");
        }

        if (evaluation.getDeliveryDays() < 0) {
            throw new ValidationException(">= 0");
        }

        if (evaluation.getScore() < 0 || evaluation.getScore() > 100) {
            throw new ValidationException("between 0 and 100");
        }

        SLARequirement sla = requirementRepository
                .findById(evaluation.getSlaRequirement().getId())
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        evaluation.setMeetsDeliveryTarget(
                evaluation.getDeliveryDays() <= sla.getDeliveryDays());

        evaluation.setMeetsQualityTarget(
                evaluation.getScore() >= sla.getQualityScore());

        return evaluationRepository.save(evaluation);
    }

    @Override
    public DeliveryEvaluation getEvaluationById(Long id) {
        return evaluationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
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
