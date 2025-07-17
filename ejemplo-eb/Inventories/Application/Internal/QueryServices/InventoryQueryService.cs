using ejemplo_eb.Inventories.Domain.Repository;
using ejemplo_eb.Inventories.Domain.Services;
using EjemploEb.Inventories.Domain.Model.Aggregates;

namespace ejemplo_eb.Inventories.Application.Internal.QueryServices
{
    public class GetProductByIdQueryService : IInventoryQueryService
    {
        private readonly IInventoryRepository _repository;

        public GetProductByIdQueryService(IInventoryRepository repository)
        {
            _repository = repository;
        }

        public async Task<Product?> GetProductByIdAsync(int id)
        {
            return await _repository.FindByIdAsync(id);
        }

        public async Task<bool> ExistsByProductNumberAsync(string productNumber)
        {
            return await _repository.ExistsByProductNumberAsync(productNumber);
        }
    }
}
