using EjemploEb.Inventories.Domain.Model.Aggregates;

namespace ejemplo_eb.Inventories.Domain.Repository
{
    public interface IInventoryRepository
    {
        Task AddAsync(Product product);
        Task<Product?> FindByIdAsync(int id);
        Task<bool> ExistsByProductNumberAsync(string productNumber);

        Task<bool> ExistsByNameAsync(string name);
    }
}