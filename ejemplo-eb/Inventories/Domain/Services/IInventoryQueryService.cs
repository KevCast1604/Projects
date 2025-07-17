using EjemploEb.Inventories.Domain.Model.Aggregates;

namespace ejemplo_eb.Inventories.Domain.Services
{
    public interface IInventoryQueryService
    {
        Task<bool> ExistsByProductNumberAsync(string productNumber);
        Task<Product?> GetProductByIdAsync(int id);
        // Otros queries futuros (ListProductsAsync, SearchProductsAsync, etc)
    }
}
