@RestController
@RequestMapping("/api/evaluations")
@Tag(name = "Delivery Evaluation API")
public class DeliveryEvaluationController {

    private final DeliveryEvaluationService service;

    public DeliveryEvaluationController(DeliveryEvaluationService service) {
        this.service = service;
    }

    
    @PostMapping
    public DeliveryEvaluation create(@RequestBody DeliveryEvaluation evaluation) {

        if (evaluation.getVendor() == null || evaluation.getVendor().getId() == null) {
            throw new IllegalArgumentException("Vendor ID is required");
        }

        if (evaluation.getSlaRequirement() == null || evaluation.getSlaRequirement().getId() == null) {
            throw new IllegalArgumentException("SLA Requirement ID is required");
        }

        return service.createEvaluation(
                evaluation.getVendor().getId(),
                evaluation.getSlaRequirement().getId(),
                evaluation.getActualDeliveryDays(),
                evaluation.getQualityScore(),
                evaluation.getEvaluationDate()
        );
    }

    
    @GetMapping("/{id}")
    public DeliveryEvaluation getById(@PathVariable Long id) {
        return service.getEvaluationById(id);
    }

    @GetMapping("/by-vendor")
    public List<DeliveryEvaluation> getByVendor(
            @RequestParam Long vendorId) {

        return service.getEvaluationsForVendor(vendorId);
    }

    
    @GetMapping("/by-requirement")
    public List<DeliveryEvaluation> getByRequirement(
            @RequestParam Long requirementId) {

        return service.getEvaluationsForRequirement(requirementId);
    }

    
    @GetMapping("/high-quality")
    public List<DeliveryEvaluation> getHighQualityDeliveries(
            @RequestParam Long vendorId,
            @RequestParam Double minScore) {

        return service.getHighQualityDeliveries(vendorId, minScore);
    }

    
    @GetMapping("/on-time")
    public List<DeliveryEvaluation> getOnTimeDeliveries(
            @RequestParam Long slaId) {

        return service.getOnTimeDeliveries(slaId);
    }
}
