using ejemplo_eb.Inventories.Domain.Services;

namespace ejemplo_eb.Manufacturing.Interfaces.ACL
{
    public interface IInventoryAclService
    {
        Task<bool> ProductExistsAsync(string productNumber);
    }

    public class InventoryAclService : IInventoryAclService
    {
        private readonly IInventoryQueryService _inventoryQueryService;

        public InventoryAclService(IInventoryQueryService inventoryQueryService)
        {
            _inventoryQueryService = inventoryQueryService;
        }

        public async Task<bool> ProductExistsAsync(string productNumber)
        {
            return await _inventoryQueryService.ExistsByProductNumberAsync(productNumber);
        }
    }
}
