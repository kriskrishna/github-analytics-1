import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method DELETE()
        url "/issues"
    }
    response {
        status 200
    }
}