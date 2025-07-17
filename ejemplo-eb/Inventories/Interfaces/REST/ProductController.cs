using ejemplo_eb.Inventories.Domain.Services;
using ejemplo_eb.Inventories.Interfaces.REST.Resources;
using ejemplo_eb.Inventories.Interfaces.REST.Transform;
using Microsoft.AspNetCore.Mvc;

namespace ejemplo_eb.Inventories.Interfaces.REST
{
    [ApiController]
    [Route("api/v1/products")]
    public class ProductsController : ControllerBase
    {
        private readonly IInventoryCommandService _commandService;
        private readonly IInventoryQueryService _queryService;

        public ProductsController(IInventoryCommandService commandService, IInventoryQueryService queryService)
        {
            _commandService = commandService;
            _queryService = queryService;
        }

        // POST /api/v1/products
        [HttpPost]
        public async Task<IActionResult> CreateProduct([FromBody] CreateProductResource resource)
        {
            var command = CreateProductCommandFromResourceAssembler.ToCommand(resource);

            var product = await _commandService.CreateProductAsync(command);

            var productResource = ProductResourceFromEntityAssembler.FromEntity(product);

            return CreatedAtAction(nameof(GetProductById), new { id = product.Id }, productResource);
        }

        // GET /api/v1/products/{id}
        [HttpGet("{id:int}")]
        public async Task<IActionResult> GetProductById(int id)
        {
            var product = await _queryService.GetProductByIdAsync(id);

            if (product == null)
                return NotFound();

            var productResource = ProductResourceFromEntityAssembler.FromEntity(product);

            return Ok(productResource);
        }
    }
}
