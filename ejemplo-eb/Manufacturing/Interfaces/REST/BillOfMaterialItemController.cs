using ejemplo_eb.Manufacturing.Application.Internal.CommandServices;
using ejemplo_eb.Manufacturing.Application.Internal.QueryServices;
using ejemplo_eb.Manufacturing.Domain.Services;
using ejemplo_eb.Manufacturing.Interfaces.REST.Resources;
using ejemplo_eb.Manufacturing.Interfaces.REST.Transforms;
using Microsoft.AspNetCore.Mvc;

namespace ejemplo_eb.Manufacturing.Interfaces.REST.Controllers
{
    [ApiController]
    [Route("api/v1/bill-of-materials-items")]
    public class BillOfMaterialsItemController : ControllerBase
    {
        private readonly IBillOfMaterialsItemCommandService _commandService;
        private readonly IBillOfMaterialsItemQueryService _queryService;

        public BillOfMaterialsItemController(
            IBillOfMaterialsItemCommandService commandService,
            IBillOfMaterialsItemQueryService queryService)
        {
            _commandService = commandService;
            _queryService = queryService;
        }

        [HttpPost]
        public async Task<IActionResult> Create([FromBody] CreateBillOfMaterialsItemResource resource)
        {
            try
            {
                var command = resource.ToCommand();
                var createdItem = await _commandService.CreateAsync(command);
                var responseResource = createdItem.ToResource();

                return CreatedAtAction(nameof(GetById), new { id = responseResource.Id }, responseResource);
            }
            catch (Exception ex)
            {
                // Aquí puedes personalizar errores según el tipo
                return BadRequest(new { error = ex.Message });
            }
        }

        [HttpGet("{id:int}")]
        public async Task<IActionResult> GetById(int id)
        {
            var item = await _queryService.GetByIdAsync(id);
            if (item == null) return NotFound();

            var resource = item.ToResource();
            return Ok(resource);
        }
    }
}
