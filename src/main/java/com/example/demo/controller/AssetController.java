@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @PostMapping
    public Asset create(
            @RequestBody Asset asset,
            @RequestParam Long vendorId,
            @RequestParam Long depreciationRuleId) {

        return assetService.createAsset(
                asset,
                vendorId,
                depreciationRuleId
        );
    }
}
