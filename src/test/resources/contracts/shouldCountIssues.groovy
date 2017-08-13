import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method GET()
        url "/issues/count"
    }
    response {
        status 200
        body 5
    }
}