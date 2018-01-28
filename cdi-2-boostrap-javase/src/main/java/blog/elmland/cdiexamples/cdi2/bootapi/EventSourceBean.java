/*******************************************************************************
 * MIT License
 *
 * Copyright (c) 2018 Florian Schmidt / https://elmland.blog
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *******************************************************************************/
package blog.elmland.cdiexamples.cdi2.bootapi;

import java.util.Random;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventSourceBean {
    private static final Logger logger = LoggerFactory.getLogger(EventSourceBean.class);

    private long id = 0;

    @Inject
    private Event<String> stringEvent;

    public void doSomeStuff() {
        String eventPayload = "I'm a test event - " + new Random().nextInt();

        logger.info("[{}] Fire event '{}'", id, eventPayload);
        this.stringEvent.fire(eventPayload);
    }

    @PostConstruct
    public void init() {
        this.id = System.currentTimeMillis();
    }

    @PreDestroy
    public void dinit() {
        logger.info("EventSourceBean preDestroy executed");
    }
}
